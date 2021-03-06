package com.ikalimullin.base.employee.list.domain.model

import com.ikalimullin.entity.employee.Employee

internal data class EmployeeListState(
    val isLoading: Boolean,
    val employees: List<Employee>?,
    val filteredEmployees: List<Employee>?,
    val sortingType: SortingType,
    val error: Throwable?,
    val searchText: String
)
