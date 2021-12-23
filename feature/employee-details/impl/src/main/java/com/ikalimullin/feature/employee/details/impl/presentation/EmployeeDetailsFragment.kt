package com.ikalimullin.feature.employee.details.impl.presentation

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ikalimullin.core.view.fragment.withInitialArguments
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
            val profession: String,
            val telephone: String,
            val birthdate: String
        ) : Parcelable

        fun newInstance(employee: Employee) =
            EmployeeDetailsFragment().withInitialArguments(employee)
    }

    private val viewBinding by viewBinding(FragmentEmployeeDetailsBinding::bind)

    private val viewModel by viewModels<EmployeeDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
