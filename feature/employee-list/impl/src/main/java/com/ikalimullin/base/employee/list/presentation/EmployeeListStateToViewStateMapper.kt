package com.ikalimullin.base.employee.list.presentation

import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.base.employee.list.presentation.page.EmployeeItem
import com.ikalimullin.core.mvi.Mapper
import javax.inject.Inject

private const val COUNT_EMPLOYEE_SHIMMER = 10
internal typealias EmployeeViewStateMapper = Mapper<EmployeeListState, EmployeeListViewState>

internal class EmployeeListStateToViewStateMapper @Inject constructor() : EmployeeViewStateMapper {

    override fun invoke(state: EmployeeListState): EmployeeListViewState {

        val items = mutableListOf<EmployeeItem>().apply {
            if (state.error != null) {
                add(EmployeeItem.Error)
            } else {
                val employees = state.filteredEmployees ?: state.employees
                employees?.forEach { employee ->
                    add(
                        EmployeeItem.Data(
                            name = "${employee.firstName} ${employee.lastName}",
                            profession = employee.department.value,
                            avatarUrl = employee.avatarUrl
                        )
                    )
                }
            } ?: repeat(COUNT_EMPLOYEE_SHIMMER) { add(EmployeeItem.Shimmer) }
        }

        return EmployeeListViewState(items)
    }
}
