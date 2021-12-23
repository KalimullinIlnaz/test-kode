package com.ikalimullin.base.employee.list.domain.model

import com.ikalimullin.core.mvi.Reducer

internal class EmployeeListReducer : Reducer<EmployeeListEffect, EmployeeListState> {

    companion object {
        fun initState() = EmployeeListState(
            employees = null,
            filteredEmployees = null,
            sortingType = SortingType.DEFAULT,
            error = null,
            searchText = "",
            isLoading = true
        )
    }

    private val sortingReducer = EmployeeSortingReducer()

    override fun invoke(
        effect: EmployeeListEffect,
        state: EmployeeListState
    ) = when (effect) {
        is EmployeeListEffect.NavigateToDetails,
        EmployeeListEffect.LoadEmployees -> state.copy(
            isLoading = true
        )
        is EmployeeListEffect.EmployeesLoadFailure -> state.copy(
            error = effect.error,
            isLoading = false
        )
        is EmployeeListEffect.EmployeesLoadSuccess -> state.copy(
            employees = effect.employees,
            error = null,
            isLoading = false
        )
        is EmployeeListEffect.SetSearchText -> state.copy(searchText = effect.text)
        is EmployeeListEffect.SetFilter -> when (val filter = effect.filter) {
            EmployeeListEffect.SetFilter.Filter.All -> state.copy(filteredEmployees = null)
            is EmployeeListEffect.SetFilter.Filter.Profession -> state.copy(
                filteredEmployees = state.employees?.filter { employee ->
                    employee.department == filter.department
                }
            )
        }
        is EmployeeListEffect.Sorting -> sortingReducer(effect, state)
    }
}
