package com.ikalimullin.base.employee.list.domain.model.middlewares

import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.forward
import com.ikalimullin.base.employee.list.domain.model.EmployeeListEffect
import com.ikalimullin.base.employee.list.domain.model.EmployeeListMiddleware
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.core.coroutines.extensions.emptyFlatMap
import com.ikalimullin.feature.employee.details.api.EmployeeDetailsScreenProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class NavigateToEmployeeDetailsMiddleware @Inject constructor(
    private val modo: Modo,
    private val dispatchersProvider: DispatchersProvider
) : EmployeeListMiddleware {

    override fun invoke(
        effects: Flow<EmployeeListEffect>,
        states: Flow<EmployeeListState>
    ) = effects
        .filterIsInstance<EmployeeListEffect.NavigateToDetails>()
        .onEach {
            withContext(dispatchersProvider.main) {
                modo.forward(EmployeeDetailsScreenProvider.employeeDetailsScreen())
            }
        }
        .emptyFlatMap()
}
