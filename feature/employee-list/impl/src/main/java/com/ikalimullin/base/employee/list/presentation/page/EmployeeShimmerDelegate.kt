package com.ikalimullin.base.employee.list.presentation.page

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.ikalimullin.base.employee.list.databinding.ItemEmployeeShimmerBinding

internal fun employeeShimmerDelegate() =
    adapterDelegateViewBinding<EmployeeItem.Shimmer, EmployeeItem, ItemEmployeeShimmerBinding>(
        { layoutInflater, root -> ItemEmployeeShimmerBinding.inflate(layoutInflater, root, false) }
    ) {
    }
