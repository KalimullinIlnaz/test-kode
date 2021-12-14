package com.ikalimullin.base.employee.list.presentation

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

internal class EmployeeListAdapter : AsyncListDifferDelegationAdapter<EmployeeItem>(DiffUtil) {

    init {
        delegatesManager
            .addDelegate(employeeDelegate())
    }
}

private object DiffUtil : DiffUtil.ItemCallback<EmployeeItem>() {

    override fun areItemsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem) =
        oldItem::class.java == newItem::class.java

    override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem) =
        oldItem == newItem
}
