package com.ikalimullin.base.employee.list.presentation.page

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.databinding.ItemEmployeeBinding
import com.ikalimullin.core.view.glide.ImageUtils.loadImage

internal fun employeeDelegate(navigateToDetails: () -> Unit) =
    adapterDelegateViewBinding<EmployeeItem.Data, EmployeeItem, ItemEmployeeBinding>(
        { layoutInflater, root -> ItemEmployeeBinding.inflate(layoutInflater, root, false) }
    ) {

        binding.root.setOnClickListener {
            if (absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                navigateToDetails()
            }
        }

        bind {
            with(binding) {
                name.text = item.name
                profession.text = item.profession
                avatar.loadImage(
                    requestOptions = RequestOptions()
                        .circleCrop()
                        .error(R.drawable.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL),
                    url = item.avatarUrl
                )
                birthday.text
            }
        }
    }
