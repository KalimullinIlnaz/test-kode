package com.ikalimullin.base.employee.list.presentation.page.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.ikalimullin.base.employee.list.presentation.page.employeeShimmerDelegate

internal class EmployeeListAdapter(
    navigateToDetails: (id: String) -> Unit,
    refresh: () -> Unit
) : AsyncListDifferDelegationAdapter<EmployeeItem>(DiffUtilCallback) {

    init {
        delegatesManager
            .addDelegate(employeeDelegate(navigateToDetails))
            .addDelegate(employeeShimmerDelegate())
            .addDelegate(employeeErrorDelegate(refresh))
            .addDelegate(employeeBirthdayDividerDelegate())
            .addDelegate(employeeNotFoundDelegate())
    }
}

private object DiffUtilCallback : DiffUtil.ItemCallback<EmployeeItem>() {

    override fun areItemsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem) =
        oldItem::class.java == newItem::class.java

    override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem) =
        oldItem == newItem
}
