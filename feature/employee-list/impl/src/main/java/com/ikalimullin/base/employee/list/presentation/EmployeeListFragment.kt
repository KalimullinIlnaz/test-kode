package com.ikalimullin.base.employee.list.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.core.coroutines.extensions.subscribeWithStartedState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    private val viewModel by viewModels<EmployeeListViewModel>()

    private val screenAdapter by lazy {
        EmployeeListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateFlow
            .onEach(::handleState)
            .subscribeWithStartedState(viewLifecycleOwner)
    }

    private fun handleState(state: EmployeeListState) {
    }
}
