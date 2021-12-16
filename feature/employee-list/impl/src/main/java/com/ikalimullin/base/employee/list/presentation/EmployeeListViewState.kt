package com.ikalimullin.base.employee.list.presentation

import com.ikalimullin.base.employee.list.presentation.page.EmployeeItem

internal data class EmployeeListViewState(
    val items: List<EmployeeItem>,
    val isAlphabeticallySortingChecked: Boolean,
    val isBirthdaySortingChecked: Boolean,
    val isDefaultSortingChecked: Boolean,
)
