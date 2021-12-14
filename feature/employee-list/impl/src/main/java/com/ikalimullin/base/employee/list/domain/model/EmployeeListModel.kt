package com.ikalimullin.base.employee.list.domain.model

import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.core.mvi.Middleware
import com.ikalimullin.core.mvi.makeStore
import javax.inject.Inject

internal typealias EmployeeListMiddleware = Middleware<
    @JvmSuppressWildcards EmployeeListEffect,
    @JvmSuppressWildcards EmployeeListState
    >

internal class EmployeeListModel @Inject constructor(
    dispatchersProvider: DispatchersProvider,
    middlewares: Set<EmployeeListMiddleware>
) : EmployeeListInteractor {

    private val store = makeStore(
        dispatchersProvider = dispatchersProvider,
        initState = EmployeeListReducer.initState(),
        initEffects = listOf(EmployeeListEffect.LoadEmployees),
        middlewares = middlewares,
        reducer = EmployeeListReducer(),
        actionToEffect = ::actionToEffect
    )

    override val stateFlow = store.stateFlow

    override fun action(action: EmployeeListAction) = store.action(action)

    override fun init() = store.init()

    override fun dispose() = store.dispose()
}

private fun actionToEffect(action: EmployeeListAction): EmployeeListEffect = when (action) {
    EmployeeListAction.Refresh -> EmployeeListEffect.LoadEmployees
    is EmployeeListAction.Search -> EmployeeListEffect.SetSearchText(action.text)
    EmployeeListAction.Sort -> EmployeeListEffect.OpenSortDialog
}
