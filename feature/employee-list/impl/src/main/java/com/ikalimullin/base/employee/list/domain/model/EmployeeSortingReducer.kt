package com.ikalimullin.base.employee.list.domain.model

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
        compareBy(String.CASE_INSENSITIVE_ORDER) { employee -> employee.firstName }
    )

    private fun sortByBirthday(employees: List<Employee>?) =
        employees?.sortedWith { employee1, employee2 ->
            val birthday1 = employee1.birthday
            val birthday2 = employee2.birthday

            when {
                birthday1 == null && birthday2 != null -> return@sortedWith 1
                birthday1 != null && birthday2 == null -> return@sortedWith -1
                birthday1 == null && birthday2 == null -> return@sortedWith 0
            }

            val (currentDayOfMonth, currentMonth) = LocalDate.now().let { localDate ->
                localDate.dayOfMonth to localDate.month.value
            }

            val birthdayDayOfMonth1 = requireNotNull(birthday1).dayOfMonth
            val birthdayMonth1 = birthday1.month.value

            val birthdayDayOfMonth2 = requireNotNull(birthday2).dayOfMonth
            val birthdayMonth2 = birthday2.month.value


            1
        }
}
