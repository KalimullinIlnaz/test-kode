package com.ikalimullin.base.employee.list.data.repository

import com.ikalimullin.base.employee.list.data.EmployeeNetworkApi
import com.ikalimullin.base.employee.list.data.toDomain
import com.ikalimullin.base.employee.list.domain.repository.EmployeeNetworkRepository
import com.ikalimullin.core.coroutines.DispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class EmployeeNetworkRepositoryImpl @Inject constructor(
    private val employeeNetworkApi: EmployeeNetworkApi,
    private val dispatchersProvider: DispatchersProvider
) : EmployeeNetworkRepository {

    override suspend fun getEmployees() = withContext(dispatchersProvider.io) {
        employeeNetworkApi.getEmployeeAsync().await()
            .map { list -> list.map { employeeDto -> employeeDto.toDomain() } }
    }
}
