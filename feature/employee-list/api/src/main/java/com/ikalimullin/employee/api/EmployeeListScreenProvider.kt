package com.ikalimullin.employee.api

import com.github.terrakok.modo.Screen
import com.ikalimullin.base.employee.list.presentation.list.EmployeeListScreen

object EmployeeListScreenProvider {
    fun employeeListScreen(): Screen = EmployeeListScreen()
}
