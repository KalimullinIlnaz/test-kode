package com.ikalimullin.base.employee.list.presentation.page.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.ikalimullin.base.employee.list.databinding.ItemEmployeeErrorBinding

internal fun employeeErrorDelegate(refresh: () -> Unit) =
    adapterDelegateViewBinding<EmployeeItem.Error, EmployeeItem, ItemEmployeeErrorBinding>(
        { layoutInflater, root -> ItemEmployeeErrorBinding.inflate(layoutInflater, root, false) }
    ) {
        binding.tryAgainButton.setOnClickListener { refresh() }
    }
