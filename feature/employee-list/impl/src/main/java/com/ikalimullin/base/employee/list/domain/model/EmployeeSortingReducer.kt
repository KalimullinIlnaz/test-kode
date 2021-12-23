package com.ikalimullin.base.employee.list.domain.model

import com.ikalimullin.core.mvi.Reducer

internal class EmployeeSortingReducer : Reducer<EmployeeListEffect.Sorting, EmployeeListState> {

    override fun invoke(
        effect: EmployeeListEffect.Sorting,
        state: EmployeeListState
    ) = when (effect) {
        EmployeeListEffect.Sorting.OpenScreen -> state
        is EmployeeListEffect.Sorting.Set -> {
            val sortingType = effect.sortingType
            val filteredEmployees = when (sortingType) {
                SortingType.ALPHABETICALLY -> {
                    val employees = state.filteredEmployees ?: state.filteredEmployees
                    employees?.sortedWith(
                        compareBy(String.CASE_INSENSITIVE_ORDER) { employee -> employee.firstName }
                    )
                }
                SortingType.BIRTHDAY -> state.filteredEmployees ?: state.employees // TODO доработать
                SortingType.DEFAULT -> state.filteredEmployees ?: state.employees
            }
            state.copy(
                filteredEmployees = filteredEmployees,
                sortingType = sortingType
            )
        }
    }
}
