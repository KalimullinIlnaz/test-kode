package com.ikalimullin.base.employee.list.presentation

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.ikalimullin.base.employee.list.databinding.ItemEmployeeBinding

internal fun employeeDelegate() =
    adapterDelegateViewBinding<EmployeeItem, EmployeeItem, ItemEmployeeBinding>(
        { layoutInflater, root -> ItemEmployeeBinding.inflate(layoutInflater, root, false) }
    ) {

        bind {
            with(binding) {
                name.text = item.name
                profession.text = item.profession
            }
        }
    }
