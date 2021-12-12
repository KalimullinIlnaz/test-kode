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
        oldItem.employee.id == newItem.employee.id

    override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem) =
        oldItem.employee == newItem.employee
}
