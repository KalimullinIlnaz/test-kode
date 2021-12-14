package com.ikalimullin.base.employee.list.data.repository

import com.ikalimullin.base.employee.list.data.EmployeeNetworkApi
import com.ikalimullin.base.employee.list.data.toDomain
import com.ikalimullin.base.employee.list.domain.repository.EmployeeNetworkRepository
import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.core.network.awaitResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class EmployeeNetworkRepositoryImpl @Inject constructor(
    private val employeeNetworkApi: EmployeeNetworkApi,
    private val dispatchersProvider: DispatchersProvider
) : EmployeeNetworkRepository {

    override suspend fun getEmployees() = withContext(dispatchersProvider.io) {
        employeeNetworkApi.getEmployeeAsync().awaitResponse {
            items.map { employee -> employee.toDomain() }
        }
    }
}
