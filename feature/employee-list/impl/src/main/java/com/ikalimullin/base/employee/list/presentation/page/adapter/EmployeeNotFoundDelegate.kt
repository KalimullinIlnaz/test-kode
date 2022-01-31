package com.ikalimullin.base.employee.list.presentation.page.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.ikalimullin.base.employee.list.databinding.ItemEmployeeNotFoundBinding

internal fun employeeNotFoundDelegate() =
    adapterDelegateViewBinding<EmployeeItem.NotFound, EmployeeItem, ItemEmployeeNotFoundBinding>(
        { layoutInflater, root -> ItemEmployeeNotFoundBinding.inflate(layoutInflater, root, false) }
    ) {
    }
