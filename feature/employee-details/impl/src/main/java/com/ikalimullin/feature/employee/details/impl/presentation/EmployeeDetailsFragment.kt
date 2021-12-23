package com.ikalimullin.feature.employee.details.impl.presentation

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.ikalimullin.core.stdlib.delegates.unsafeLazy
import com.ikalimullin.core.view.fragment.initialArguments
import com.ikalimullin.core.view.fragment.withInitialArguments
import com.ikalimullin.core.view.glide.ImageUtils.loadImage
import com.ikalimullin.core.view.viewBinding.viewBinding
import com.ikalimullin.feature.employee.details.impl.R
import com.ikalimullin.feature.employee.details.impl.databinding.FragmentEmployeeDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class EmployeeDetailsFragment : Fragment(R.layout.fragment_employee_details) {

    companion object {

        @Parcelize
        data class Employee(
            val avatarUrl: String,
            val name: String,
            val position: String,
            val telephone: String,
            val birthday: String
        ) : Parcelable

        fun newInstance(employee: Employee) =
            EmployeeDetailsFragment().withInitialArguments(employee)
    }

    private val viewBinding by viewBinding(FragmentEmployeeDetailsBinding::bind)

    private val viewModel by viewModels<EmployeeDetailsViewModel>()

    private val args by unsafeLazy { initialArguments<Employee>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        avatar.loadImage(
            url = args.avatarUrl,
            requestOptions = RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        name.text = args.name
        position.text = args.position
        birthday.text = args.birthday
    }
}
