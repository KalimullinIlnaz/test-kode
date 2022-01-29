package com.ikalimullin.base.employee.list.presentation.page

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.ikalimullin.base.employee.list.databinding.ItemEmployeeBirthdayDividerBinding
import com.ikalimullin.core.view.textView.newText

internal fun employeeBirthdayDividerDelegate() =
    adapterDelegateViewBinding<EmployeeItem.BirthdayDivider, EmployeeItem, ItemEmployeeBirthdayDividerBinding>(
        { layoutInflater, root ->
            ItemEmployeeBirthdayDividerBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {

        bind {
            with(binding) {
                birthdayYear.newText = item.year
            }
        }
    }
