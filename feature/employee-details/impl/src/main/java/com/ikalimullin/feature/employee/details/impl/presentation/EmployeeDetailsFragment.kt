package com.ikalimullin.feature.employee.details.impl.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ikalimullin.core.view.viewBinding.viewBinding
import com.ikalimullin.feature.employee.details.impl.R
import com.ikalimullin.feature.employee.details.impl.databinding.FragmentEmployeeDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeDetailsFragment : Fragment(R.layout.fragment_employee_details) {

    companion object {
        fun newInstance() = EmployeeDetailsFragment()
    }

    private val viewBinding by viewBinding(FragmentEmployeeDetailsBinding::bind)

    private val viewModel by viewModels<EmployeeDetailsViewModel>({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
