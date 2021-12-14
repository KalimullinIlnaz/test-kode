package com.ikalimullin.base.employee.list.domain.model.middlewares

import com.ikalimullin.base.employee.list.domain.model.EmployeeListEffect
import com.ikalimullin.base.employee.list.domain.model.EmployeeListMiddleware
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.base.employee.list.domain.repository.EmployeeNetworkRepository
import com.ikalimullin.entity.network.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class LoadEmployeeMiddleware @Inject constructor(
    private val employeeNetworkRepository: EmployeeNetworkRepository
) : EmployeeListMiddleware {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(
        effects: Flow<EmployeeListEffect>,
        states: Flow<EmployeeListState>
    ) = effects
        .filterIsInstance<EmployeeListEffect.LoadEmployees>()
        .map {
            when (val result = employeeNetworkRepository.getEmployees()) {
                is NetworkResult.Error -> EmployeeListEffect.EmployeesLoadFailure(result.error)
                is NetworkResult.Success -> EmployeeListEffect.EmployeesLoadSuccess(result.value)
            }
        }
}
