package com.ikalimullin.base.employee.list.domain.model

internal sealed class EmployeeListAction {
    object Refresh : EmployeeListAction()

    sealed class Sorting : EmployeeListAction() {
        object OpenScreen : Sorting()
        data class Set(val sortingType: SortingType) : EmployeeListAction()
    }

    class Search(val text: String) : EmployeeListAction()
    class TabSelected(val tabText: String) : EmployeeListAction()
    class NavigateToDetails(val id: String) : EmployeeListAction()
}
