package com.ikalimullin.core.mvi

import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.mvi.BuildConfig
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import timber.log.Timber

class MviStore<ACTION, EFFECT, STATE>(
    initState: STATE,
    private val dispatchersProvider: DispatchersProvider,
    private val middlewares: Set<Middleware<EFFECT, STATE>>,
    private val reducer: Reducer<EFFECT, STATE>,
    private val initEffects: List<EFFECT>,
    private val actionToEffect: (ACTION) -> EFFECT,
    private val errorHandler: suspend FlowCollector<EFFECT>.(Throwable) -> Unit = {},
) : Store {

    private val coroutineScope = CoroutineScope(
        SupervisorJob() +
            dispatchersProvider.main +
            CoroutineName(MviStore::class.java.simpleName)
    )

    private val state = MutableStateFlow(initState)
    private val effects = MutableSharedFlow<EFFECT>()
    private val internalEffects = MutableSharedFlow<EFFECT>()
    private val mutex = Mutex()

    val stateFlow: Flow<STATE> = state

    fun action(action: ACTION) {
        coroutineScope.launch {
            effects.emit(actionToEffect(action))
        }
    }

    override fun init() {
        coroutineScope.launch {
            val initEffects = initEffects.asFlow()
            val middleFlow = middlewares
                .map { middleware -> dispatch(middleware, initEffects) }
                .merge()
                .onEach { effect ->
                    coroutineScope.launch {
                        internalEffects.emit(effect)
                    }
                }

            listOf(initEffects, effects, middleFlow)
                .merge()
                .onEach(::reduce)
                .collect()
        }
    }

    override fun dispose() = coroutineScope.cancel()

    private suspend fun reduce(effect: EFFECT) = withContext(dispatchersProvider.default) {
        mutex.withLock {
            state.emit(reducer(effect, state.value))
        }
    }

    private fun dispatch(
        middleware: Middleware<EFFECT, STATE>,
        initEffects: Flow<EFFECT>
    ) = middleware(
        listOf(initEffects, effects.asSharedFlow(), internalEffects.asSharedFlow()).merge(),
        state.asStateFlow()
    )
        .flowOn(dispatchersProvider.default)
        .catch { throwable ->
            Timber.e(throwable)
            errorHandler(this, throwable)

            if (BuildConfig.DEBUG) throw throwable
        }
}

fun <STATE, EFFECT, ACTION> makeStore(
    dispatchersProvider: DispatchersProvider,
    initState: STATE,
    middlewares: Set<Middleware<EFFECT, STATE>>,
    reducer: Reducer<EFFECT, STATE>,
    initEffects: List<EFFECT>,
    actionToEffect: (ACTION) -> EFFECT,
    errorHandler: suspend FlowCollector<EFFECT>.(Throwable) -> Unit = {}
) = MviStore(
    dispatchersProvider = dispatchersProvider,
    initState = initState,
    middlewares = middlewares,
    reducer = reducer,
    initEffects = initEffects,
    actionToEffect = actionToEffect,
    errorHandler = errorHandler
)
