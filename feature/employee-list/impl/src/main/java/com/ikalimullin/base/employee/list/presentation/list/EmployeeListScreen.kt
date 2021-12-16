package com.ikalimullin.base.employee.list.presentation.list

import com.github.terrakok.modo.android.AppScreen
import kotlinx.parcelize.Parcelize

@Parcelize
class EmployeeListScreen : AppScreen("EmployeeList") {
    override fun create() = EmployeeListFragment.newInstance()
}
