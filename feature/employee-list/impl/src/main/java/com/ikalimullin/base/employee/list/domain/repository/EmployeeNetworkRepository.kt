package com.ikalimullin.base.employee.list.domain.repository

import сom.ikalimullin.entity.employee.Employee

internal interface EmployeeNetworkRepository {

    suspend fun getEmployees(): Result<List<Employee>>
}
