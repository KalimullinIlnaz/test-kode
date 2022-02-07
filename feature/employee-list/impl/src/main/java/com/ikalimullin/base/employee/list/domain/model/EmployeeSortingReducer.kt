package com.ikalimullin.base.employee.list.domain.model

import com.ikalimullin.core.constants.date.DateTimeUtils
import com.ikalimullin.core.mvi.Reducer
import com.ikalimullin.entity.employee.Employee
import java.time.LocalDate

internal class EmployeeSortingReducer : Reducer<EmployeeListEffect.Sorting, EmployeeListState> {

    override fun invoke(
        effect: EmployeeListEffect.Sorting,
        state: EmployeeListState
    ) = when (effect) {
        EmployeeListEffect.Sorting.OpenScreen -> state
        is EmployeeListEffect.Sorting.Set -> {
            val sortingType = effect.sortingType
            val employees = state.filteredEmployees ?: state.employees
            val filteredEmployees = when (sortingType) {
                SortingType.ALPHABETICALLY -> sortByAlphabet(employees)
                SortingType.BIRTHDAY -> sortByBirthday(employees)
                SortingType.DEFAULT -> employees
            }
            state.copy(
                filteredEmployees = filteredEmployees,
                sortingType = sortingType
            )
        }
    }

    private fun sortByAlphabet(employees: List<Employee>?) = employees?.sortedWith(
        compareBy(String.CASE_INSENSITIVE_ORDER) { employee ->
            "${employee.firstName} ${employee.lastName}"
        }
    )

    private fun sortByBirthday(employees: List<Employee>?) = mutableListOf<Employee>().apply {
        employees ?: return@apply

        val now = LocalDate.now()

        val beforeBirthdayEmployeeList = mutableListOf<Employee>()
        val afterBirthdayEmployeeList = mutableListOf<Employee>()

        employees.forEach { employee ->
            val birthday = employee.birthday.mapEmployeeBirthday()

            if (birthday?.isBefore(now) == true) {
                beforeBirthdayEmployeeList.add(employee)
            } else {
                afterBirthdayEmployeeList.add(employee)
            }
        }

        addAll(afterBirthdayEmployeeList.sortEmployees())
        addAll(beforeBirthdayEmployeeList.sortEmployees())
    }
}

private fun List<Employee>.sortEmployees() = sortedBy { employee ->
    employee.birthday.mapEmployeeBirthday()
}

private fun LocalDate?.mapEmployeeBirthday() = this?.let { localDate ->
    DateTimeUtils.createDateWithCurrentYear(localDate)
}
