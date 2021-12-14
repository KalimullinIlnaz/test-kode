package com.ikalimullin.base.employee.list.presentation.page

sealed class EmployeeItem {

    data class Data(
        val name: String,
        val profession: String,
        val avatarUrl: String
    ) : EmployeeItem()

    object Shimmer : EmployeeItem()
}
