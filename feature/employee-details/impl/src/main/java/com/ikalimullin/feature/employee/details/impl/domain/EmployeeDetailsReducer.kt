package com.ikalimullin.feature.employee.details.impl.domain

import com.ikalimullin.core.mvi.Reducer

internal class EmployeeDetailsReducer : Reducer<EmployeeDetailsEffect, EmployeeDetailsState> {

    companion object {
        fun initState() = EmployeeDetailsState()
    }

    override fun invoke(
        effect: EmployeeDetailsEffect,
        state: EmployeeDetailsState
    ) = state
}
