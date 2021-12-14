package com.ikalimullin.base.employee.list.domain.model

internal sealed class EmployeeListAction {
    object Refresh : EmployeeListAction()
    object Sort : EmployeeListAction()

    class Search(val text: String) : EmployeeListAction()
}
