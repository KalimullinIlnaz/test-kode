package com.ikalimullin.base.employee.list.domain.model

import com.ikalimullin.entity.employee.Employee

internal sealed class EmployeeListEffect {
    object LoadEmployees : EmployeeListEffect()
    object OpenSortDialog : EmployeeListEffect()

    data class SetSearchText(
        val text: String
    ) : EmployeeListEffect()

    data class EmployeesLoadSuccess(
        val employees: List<Employee>
    ) : EmployeeListEffect()

    data class EmployeesLoadFailure(
        val error: Throwable
    ) : EmployeeListEffect()
}
