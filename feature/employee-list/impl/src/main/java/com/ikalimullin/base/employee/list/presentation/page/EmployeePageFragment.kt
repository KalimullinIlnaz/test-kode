package com.ikalimullin.base.employee.list.presentation.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.databinding.FragmentEmployeePageBinding
import com.ikalimullin.base.employee.list.presentation.EmployeeListViewModel
import com.ikalimullin.base.employee.list.presentation.EmployeeListViewState
import com.ikalimullin.core.coroutines.extensions.subscribeWithStartedState
import com.ikalimullin.core.view.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
internal class EmployeePageFragment : Fragment(R.layout.fragment_employee_page) {

    companion object {
        fun newInstance() = EmployeePageFragment()
    }

    private val viewBinding by viewBinding(FragmentEmployeePageBinding::bind)

    private val viewModel by viewModels<EmployeeListViewModel>()

    private val screenAdapter by lazy {
        EmployeeListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.employeePageRecyclerView.adapter = screenAdapter

        viewModel.viewState
            .onEach(::handleState)
            .subscribeWithStartedState(viewLifecycleOwner)
    }

    private fun handleState(state: EmployeeListViewState) = with(state) {
        screenAdapter.items = items
    }
}
