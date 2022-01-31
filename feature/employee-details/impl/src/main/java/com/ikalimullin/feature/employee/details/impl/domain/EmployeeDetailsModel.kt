package com.ikalimullin.feature.employee.details.impl.domain

import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.core.mvi.MviStore
import javax.inject.Inject

internal class EmployeeDetailsModel @Inject constructor(
    dispatchersProvider: DispatchersProvider
) : EmployeeDetailsInteractor {

    private val store = MviStore(
        dispatchersProvider = dispatchersProvider,
        initState = EmployeeDetailsReducer.initState(),
        initEffects = emptyList(),
        middlewares = emptySet(),
        reducer = EmployeeDetailsReducer(),
        actionToEffect = ::actionToEffect
    )

    override val stateFlow = store.stateFlow

    override fun action(action: EmployeeDetailsAction) = store.action(action)

    override fun init() = store.init()

    override fun dispose() = store.dispose()
}

private fun actionToEffect(action: EmployeeDetailsAction) = EmployeeDetailsEffect()
