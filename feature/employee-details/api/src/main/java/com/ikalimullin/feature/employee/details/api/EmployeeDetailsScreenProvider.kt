package com.ikalimullin.feature.employee.details.api

import com.github.terrakok.modo.Screen
import com.ikalimullin.feature.employee.details.impl.presentation.EmployeeDetailsFragment
import com.ikalimullin.feature.employee.details.impl.presentation.EmployeeDetailsScreen

object EmployeeDetailsScreenProvider {
    fun employeeDetailsScreen(
        employee: EmployeeDetailsFragment.Companion.Employee
    ): Screen = EmployeeDetailsScreen()
}
