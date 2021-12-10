package com.ikalimullin.employee.api

import com.github.terrakok.modo.android.AppScreen
import com.ikalimullin.base.employee.list.presentation.EmployeeListFragment
import kotlinx.parcelize.Parcelize

@Parcelize
class EmployeeListScreen : AppScreen("EmployeeList") {
    override fun create() = EmployeeListFragment()
}
