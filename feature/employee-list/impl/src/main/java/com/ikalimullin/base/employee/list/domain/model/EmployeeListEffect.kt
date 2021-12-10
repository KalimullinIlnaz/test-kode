package com.ikalimullin.base.employee.list.domain.model

import com.ikalimullin.entity.employee.Employee

internal sealed class EmployeeListEffect {
    object LoadEmployees : EmployeeListEffect()

    data class EmployeesLoadSuccess(
        val employees: List<Employee>
    ) : EmployeeListEffect()

    data class EmployeesLoadFailure(
        val error: Throwable
    ) : EmployeeListEffect()
}
