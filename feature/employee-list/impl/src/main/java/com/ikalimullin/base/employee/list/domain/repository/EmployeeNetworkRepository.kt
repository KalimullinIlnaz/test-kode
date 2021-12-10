package com.ikalimullin.base.employee.list.domain.repository

import com.ikalimullin.entity.employee.Employee

internal interface EmployeeNetworkRepository {

    suspend fun getEmployees(): Result<List<Employee>>
}
