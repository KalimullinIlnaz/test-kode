package com.ikalimullin.feature.employee.details.impl.presentation.details

import com.github.terrakok.modo.android.AppScreen
import kotlinx.parcelize.Parcelize

@Parcelize
class EmployeeDetailsScreen(
    private val employee: EmployeeDetailsFragment.Companion.Employee
) : AppScreen("EmployeeDetails") {
    override fun create() = EmployeeDetailsFragment.newInstance(employee)
}
