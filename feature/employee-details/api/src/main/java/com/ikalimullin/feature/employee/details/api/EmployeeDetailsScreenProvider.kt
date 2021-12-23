package com.ikalimullin.feature.employee.details.api

import com.github.terrakok.modo.Screen
import com.ikalimullin.core.constants.date.DateTimeUtils
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
    userTag = userTag,
    position = position,
    phone = phone,
    birthday = birthday?.let(DateTimeUtils::convertLocalDateToLong)
)
