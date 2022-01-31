package com.ikalimullin.base.employee.list.presentation.page.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.databinding.ItemEmployeeBinding
import com.ikalimullin.core.view.glide.ImageUtils.loadImage
import com.ikalimullin.core.view.textView.newText

internal fun employeeDelegate(navigateToDetails: (id: String) -> Unit) =
    adapterDelegateViewBinding<EmployeeItem.Data, EmployeeItem, ItemEmployeeBinding>(
        { layoutInflater, root -> ItemEmployeeBinding.inflate(layoutInflater, root, false) }
    ) {

        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                navigateToDetails(item.id)
            }
        }

        bind {
            with(binding) {
                avatar.loadImage(
                    url = item.avatarUrl,
                    requestOptions = RequestOptions()
                        .circleCrop()
                        .error(R.drawable.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )

                name.newText = item.name
                profession.newText = item.profession
                birthday.newText = item.birthday
                birthday.isVisible = item.birthday.isNotEmpty()
            }
        }
    }
