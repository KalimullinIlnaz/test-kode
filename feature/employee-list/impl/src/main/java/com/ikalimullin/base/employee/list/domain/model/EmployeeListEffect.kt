package com.ikalimullin.base.employee.list.domain.model

import com.ikalimullin.entity.employee.Department
import com.ikalimullin.entity.employee.Employee

internal sealed class EmployeeListEffect {
    object LoadEmployees : EmployeeListEffect()
    data class NavigateToDetails(val id: String) : EmployeeListEffect()

    sealed class Sorting : EmployeeListEffect() {
        object OpenScreen : Sorting()
        data class Set(val sortingType: SortingType) : Sorting()
    }

    data class SetSearchText(
        val text: String
    ) : EmployeeListEffect()

    data class EmployeesLoadSuccess(
        val employees: List<Employee>
    ) : EmployeeListEffect()

    data class EmployeesLoadFailure(
        val error: Throwable
    ) : EmployeeListEffect()

    data class SetFilter(
        val filter: Filter
    ) : EmployeeListEffect() {
        sealed class Filter {
            object All : Filter()
            data class Profession(val department: Department) : Filter()
        }
    }
}
