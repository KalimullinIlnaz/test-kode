package com.ikalimullin.base.employee.list.presentation

import android.content.Context
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.base.employee.list.domain.model.SortingType
import com.ikalimullin.base.employee.list.presentation.page.adapter.EmployeeItem
import com.ikalimullin.core.constants.date.DateTimeUtils
import com.ikalimullin.core.constants.date.DateTimeUtils.getMonthNumber
import com.ikalimullin.core.mvi.Mapper
import com.ikalimullin.core.stdlib.delegates.unsafeLazy
import com.ikalimullin.core.uikit.name.NameWithUserTagFactory
import com.ikalimullin.core.view.resourses.dimens.TextSize
import com.ikalimullin.core.view.resourses.getCompatColor
import com.ikalimullin.entity.employee.Employee
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import javax.inject.Inject

private const val COUNT_EMPLOYEE_SHIMMER = 10
internal typealias EmployeeViewStateMapper = Mapper<EmployeeListState, EmployeeListViewState>

internal class EmployeeListStateToViewStateMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : EmployeeViewStateMapper {

    private val mounts by unsafeLazy {
        context.resources.getStringArray(R.array.mounts)
    }

    override fun invoke(state: EmployeeListState): EmployeeListViewState {
        val employees = state.filteredEmployees ?: state.employees

        val sortingType = state.sortingType
        val isBirthdaySortingChecked = sortingType == SortingType.BIRTHDAY
        val isAlphabeticallySortingChecked = sortingType == SortingType.ALPHABETICALLY
        val isDefaultSortingChecked = sortingType == SortingType.DEFAULT

        val items = mutableListOf<EmployeeItem>().apply {
            when {
                state.error != null -> add(EmployeeItem.Error)
                state.searchText.isNotEmpty() && employees.isNullOrEmpty() -> add(EmployeeItem.NotFound)
                employees == null -> createShimmerList()
                isBirthdaySortingChecked -> createSortedByBirthdayList(employees)
                isDefaultSortingChecked || isAlphabeticallySortingChecked -> createEmployeeList(
                    employees
                )
            }
        }

        return EmployeeListViewState(
            items = items,
            isAlphabeticallySortingChecked = isAlphabeticallySortingChecked,
            isBirthdaySortingChecked = isBirthdaySortingChecked,
            isDefaultSortingChecked = sortingType == SortingType.DEFAULT,
            sortIconColor = defineSortIconColor(sortingType),
            isRefreshing = state.isLoading && state.employees == null
        )
    }

    private fun MutableList<EmployeeItem>.createSortedByBirthdayList(
        employees: List<Employee>
    ) {
        var count = 0
        val now = LocalDate.now()
        employees.forEach { employee ->
            val birthday = employee.birthday?.let { localDate ->
                DateTimeUtils.createDateWithCurrentYear(now, localDate)
            }

            if (count == 0 && birthday?.isBefore(now) == true) {
                count++
                add(EmployeeItem.BirthdayDivider((now.year + 1).toString()))
            }

            val monthNumber = birthday?.month?.getMonthNumber() ?: return
            add(
                EmployeeItem.Data(
                    id = employee.id,
                    name = createEmployeeName(employee),
                    profession = employee.department?.value.orEmpty(),
                    avatarUrl = employee.avatarUrl,
                    birthday = String.format(
                        context.getString(R.string.birthday),
                        birthday.dayOfMonth,
                        mounts[monthNumber]
                    )
                )
            )
        }
    }

    private fun MutableList<EmployeeItem>.createShimmerList() = repeat(COUNT_EMPLOYEE_SHIMMER) {
        add(EmployeeItem.Shimmer)
    }

    private fun MutableList<EmployeeItem>.createEmployeeList(employees: List<Employee>) =
        employees.forEach { employee ->
            add(
                EmployeeItem.Data(
                    id = employee.id,
                    name = createEmployeeName(employee),
                    profession = employee.department?.value.orEmpty(),
                    avatarUrl = employee.avatarUrl
                )
            )
        }

    private fun createEmployeeName(employee: Employee) =
        NameWithUserTagFactory.create(
            name = "${employee.firstName} ${employee.lastName}",
            userTag = employee.userTag,
            userTagColor = context.getCompatColor(R.color.color_97979B),
            userTagTextSize = TextSize.textSize14px
        )

    private fun defineSortIconColor(sortingType: SortingType) =
        if (sortingType != SortingType.DEFAULT) {
            R.color.purple
        } else {
            R.color.color_C3C3C6
        }
}
