package com.ikalimullin.base.employee.list.presentation.page

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

internal class EmployeeListAdapter(refresh: () -> Unit) :
    AsyncListDifferDelegationAdapter<EmployeeItem>(DiffUtil) {

    init {
        delegatesManager
            .addDelegate(employeeDelegate())
            .addDelegate(employeeShimmerDelegate())
            .addDelegate(employeeErrorDelegate(refresh))
    }
}

private object DiffUtil : DiffUtil.ItemCallback<EmployeeItem>() {

    override fun areItemsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem) =
        oldItem::class.java == newItem::class.java

    override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem) =
        oldItem == newItem
}
