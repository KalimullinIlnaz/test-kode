package com.ikalimullin.base.employee.list.presentation

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

internal class EmployeeListAdapter : AsyncListDifferDelegationAdapter<EmployeeItem>(DiffUtil) {

    init {
        delegatesManager
    }
}

private object DiffUtil : DiffUtil.ItemCallback<EmployeeItem>() {

    override fun areItemsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean {
        TODO("Not yet implemented")
    }
}
