package com.ikalimullin.base.employee.list.domain.model.middlewares

import com.github.terrakok.modo.Modo
import com.ikalimullin.base.employee.list.domain.model.EmployeeListEffect
import com.ikalimullin.base.employee.list.domain.model.EmployeeListMiddleware
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.core.coroutines.extensions.emptyFlatMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

internal class EmployeeSortingBackNavigateMiddleware @Inject constructor(
    private val modo: Modo,
    private val dispatchersProvider: DispatchersProvider
) : EmployeeListMiddleware {

    override fun invoke(
        effects: Flow<EmployeeListEffect>,
        states: Flow<EmployeeListState>
    ) = effects
        .filterIsInstance<EmployeeListEffect.Sorting>()
        .onEach { effect ->
            // TODO определить необходимость мидлвеера после выделение абстракции над навигацией над диалогами
         /*   withContext(dispatchersProvider.main) {
                when (effect) {
                    EmployeeListEffect.Sorting.OpenScreen -> modo.forward(EmployeeSortScreen())
                    EmployeeListEffect.Sorting.SetAlphabeticallySorting,
                    EmployeeListEffect.Sorting.SetBirthdaySorting -> modo.back()
                }
            }*/
        }
        .emptyFlatMap()
}
