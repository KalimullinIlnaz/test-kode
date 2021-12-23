package com.ikalimullin.base.employee.list.presentation.page

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

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
    }
}

private object DiffUtilCallback : DiffUtil.ItemCallback<EmployeeItem>() {

    override fun areItemsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem) =
        oldItem::class.java == newItem::class.java

    override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem) =
        oldItem == newItem
}
