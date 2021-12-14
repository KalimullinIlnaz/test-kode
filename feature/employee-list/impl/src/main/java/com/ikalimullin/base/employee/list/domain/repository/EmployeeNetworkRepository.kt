package com.ikalimullin.base.employee.list.domain.repository

import com.ikalimullin.entity.employee.Employee
import com.ikalimullin.entity.network.NetworkResult

internal interface EmployeeNetworkRepository {

    suspend fun getEmployees(): NetworkResult<List<Employee>>
}
