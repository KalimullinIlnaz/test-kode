package com.ikalimullin.feature.employee.details.impl.presentation

import com.github.terrakok.modo.android.AppScreen
import kotlinx.parcelize.Parcelize

@Parcelize
class EmployeeDetailsScreen : AppScreen("EmployeeDetails") {
    override fun create() = EmployeeDetailsFragment.newInstance()
}
