package com.ikalimullin.base.employee.list.presentation.page

sealed class EmployeeItem {

    data class Data(
        val id: String,
        val name: String,
        val profession: String,
        val avatarUrl: String,
        val birthday: String = "" // todo сетать в маппере
    ) : EmployeeItem()

    data class BirthdayDivider(
        val year: String
    ) : EmployeeItem()

    object Shimmer : EmployeeItem()
    object Error : EmployeeItem()
}
