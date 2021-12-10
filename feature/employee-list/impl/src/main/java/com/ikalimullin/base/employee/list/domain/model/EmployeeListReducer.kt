package com.ikalimullin.base.employee.list.domain.model

import com.ikalimullin.core.mvi.Reducer

internal class EmployeeListReducer : Reducer<EmployeeListEffect, EmployeeListState> {

    companion object {
        fun initState() = EmployeeListState(
            employees = null,
            error = null
        )
    }

    override fun invoke(
        effect: EmployeeListEffect,
        state: EmployeeListState
    ) = when (effect) {
        is EmployeeListEffect.EmployeesLoadFailure -> state.copy(
            error = effect.error
        )
        is EmployeeListEffect.EmployeesLoadSuccess -> state.copy(
            employees = effect.employees,
            error = null
        )
        EmployeeListEffect.LoadEmployees -> state
    }
}
