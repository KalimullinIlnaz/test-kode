package com.ikalimullin.base.employee.list.domain.model

import сom.ikalimullin.entity.employee.Employee

internal data class EmployeeListState(
    val employees: List<Employee>?,
    val error: Throwable?
)
