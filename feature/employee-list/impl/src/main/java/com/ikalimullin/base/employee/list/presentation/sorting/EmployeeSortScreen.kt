package com.ikalimullin.base.employee.list.presentation.sorting

import com.github.terrakok.modo.android.AppScreen
import kotlinx.parcelize.Parcelize

@Parcelize
class EmployeeSortScreen : AppScreen("EmployeeSort") {
    override fun create() = EmployeeSortBottomDialogFragment.newInstance()
}
