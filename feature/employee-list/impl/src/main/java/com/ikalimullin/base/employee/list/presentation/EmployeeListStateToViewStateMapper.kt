package com.ikalimullin.base.employee.list.presentation

import android.content.Context
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.base.employee.list.domain.model.SortingType
import com.ikalimullin.base.employee.list.presentation.page.EmployeeItem
import com.ikalimullin.core.mvi.Mapper
import com.ikalimullin.core.uikit.name.NameWithUserTagFactory
import com.ikalimullin.entity.employee.Employee
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val COUNT_EMPLOYEE_SHIMMER = 10
internal typealias EmployeeViewStateMapper = Mapper<EmployeeListState, EmployeeListViewState>

internal class EmployeeListStateToViewStateMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : EmployeeViewStateMapper {

    override fun invoke(state: EmployeeListState): EmployeeListViewState {
        val employees = state.filteredEmployees ?: state.employees

        val sortingType = state.sortingType
        val isBirthdaySortingChecked = sortingType == SortingType.BIRTHDAY
        val isAlphabeticallySortingChecked = sortingType == SortingType.ALPHABETICALLY
        val isDefaultSortingChecked = sortingType == SortingType.DEFAULT

        val items = mutableListOf<EmployeeItem>().apply {
            when {
                state.error != null -> add(EmployeeItem.Error)
                state.searchText.isNotEmpty() && state.filteredEmployees.isNullOrEmpty() -> {
                    add(EmployeeItem.NotFound)
                }
                employees == null -> repeat(COUNT_EMPLOYEE_SHIMMER) { add(EmployeeItem.Shimmer) }
                isAlphabeticallySortingChecked -> {

                }
                isDefaultSortingChecked || isBirthdaySortingChecked -> createEmployeeList(employees)
            }
        }

        return EmployeeListViewState(
            items = items,
            isAlphabeticallySortingChecked = isAlphabeticallySortingChecked,
            isBirthdaySortingChecked = isBirthdaySortingChecked,
            isDefaultSortingChecked = sortingType == SortingType.DEFAULT,
            sortIconColor = if (sortingType != SortingType.DEFAULT) {
                R.color.purple
            } else {
                R.color.color_C3C3C6
            },
            isRefreshing = state.isLoading && state.employees != null
        )
    }

    private fun MutableList<EmployeeItem>.createEmployeeList(employees: List<Employee>) {
        employees.forEach { employee ->
            val name = NameWithUserTagFactory.create(
                name = "${employee.firstName} ${employee.lastName}",
                userTag = employee.userTag,
                userTagColor = context.getColor(R.color.color_97979B),
                userTagTextSize = context.resources.getDimensionPixelSize(R.dimen.text_size_14sp)
            )
            add(
                EmployeeItem.Data(
                    id = employee.id,
                    name = name,
                    profession = employee.department?.value.orEmpty(),
                    avatarUrl = employee.avatarUrl
                )
            )
        }
    }
}
