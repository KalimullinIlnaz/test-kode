package com.ikalimullin.feature.employee.details.api

import com.github.terrakok.modo.Screen
import com.ikalimullin.entity.employee.Employee
import com.ikalimullin.feature.employee.details.impl.presentation.EmployeeDetailsFragment
import com.ikalimullin.feature.employee.details.impl.presentation.EmployeeDetailsScreen

object EmployeeDetailsScreenProvider {
    fun employeeDetailsScreen(
        employee: Employee
    ): Screen = EmployeeDetailsScreen(employee.toParcelableArgs())
}

private fun Employee.toParcelableArgs() = EmployeeDetailsFragment.Companion.Employee(
    avatarUrl = avatarUrl,
    name = "$firstName $lastName",
    position = position,
    telephone = phone,
    birthday = birthday.toString()
)
