package com.ikalimullin.feature.employee.details.impl.presentation

import com.github.terrakok.modo.android.AppScreen
import com.ikalimullin.entity.employee.Employee
import kotlinx.parcelize.Parcelize

@Parcelize
class EmployeeDetailsScreen(
    private val employee: EmployeeDetailsFragment.Companion.Employee
) : AppScreen("EmployeeDetails") {
    override fun create() = EmployeeDetailsFragment.newInstance(employee)
}
