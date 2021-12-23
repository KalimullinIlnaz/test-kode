package com.ikalimullin.feature.employee.details.impl.presentation

import com.github.terrakok.modo.android.AppScreen
import com.ikalimullin.entity.employee.Employee
import kotlinx.parcelize.Parcelize

@Parcelize
class EmployeeDetailsScreen(
    private val employee: Employee
) : AppScreen("EmployeeDetails") {
    override fun create() = EmployeeDetailsFragment.newInstance(employee.toParcelableArgs())
}

private fun Employee.toParcelableArgs() = EmployeeDetailsFragment.Companion.Employee(
    avatarUrl = avatarUrl,
    name = "$firstName $lastName",
    profession = department?.value.orEmpty(),
    telephone = phone,
    birthdate = birthdate.toString()
)