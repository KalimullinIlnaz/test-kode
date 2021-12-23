package com.ikalimullin.base.employee.list.presentation

import android.content.Context
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.base.employee.list.domain.model.SortingType
import com.ikalimullin.base.employee.list.presentation.page.EmployeeItem
import com.ikalimullin.core.mvi.Mapper
import com.ikalimullin.core.uikit.name.NameWithUserTagFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val COUNT_EMPLOYEE_SHIMMER = 10
internal typealias EmployeeViewStateMapper = Mapper<EmployeeListState, EmployeeListViewState>

internal class EmployeeListStateToViewStateMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : EmployeeViewStateMapper {

    override fun invoke(state: EmployeeListState): EmployeeListViewState {

        val items = mutableListOf<EmployeeItem>().apply {
            when {
                state.error != null -> add(EmployeeItem.Error)
                state.searchText.isNotEmpty() && state.filteredEmployees.isNullOrEmpty() -> {
                    add(EmployeeItem.NotFound)
                }
                else -> {
                    val employees = state.filteredEmployees ?: state.employees
                    employees?.forEach { employee ->
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
                    } ?: repeat(COUNT_EMPLOYEE_SHIMMER) { add(EmployeeItem.Shimmer) }
                }
            }
        }

        val sortingType = state.sortingType

        return EmployeeListViewState(
            items = items,
            isAlphabeticallySortingChecked = sortingType == SortingType.ALPHABETICALLY,
            isBirthdaySortingChecked = sortingType == SortingType.BIRTHDAY,
            isDefaultSortingChecked = sortingType == SortingType.DEFAULT,
            sortIconColor = if (sortingType != SortingType.DEFAULT) {
                R.color.purple
            } else {
                R.color.color_C3C3C6
            },
            isRefreshing = state.isLoading && state.employees != null
        )
    }
}
