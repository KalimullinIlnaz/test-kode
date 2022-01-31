package com.ikalimullin.base.employee.list.presentation

import androidx.annotation.ColorRes
import com.ikalimullin.base.employee.list.presentation.page.adapter.EmployeeItem

internal data class EmployeeListViewState(
    val items: List<EmployeeItem>,
    val isAlphabeticallySortingChecked: Boolean,
    val isBirthdaySortingChecked: Boolean,
    val isDefaultSortingChecked: Boolean,
    @ColorRes val sortIconColor: Int,
    val isRefreshing: Boolean
)
