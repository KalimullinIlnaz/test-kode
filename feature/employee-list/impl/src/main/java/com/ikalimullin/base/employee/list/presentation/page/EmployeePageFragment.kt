package com.ikalimullin.base.employee.list.presentation.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.databinding.FragmentEmployeePageBinding
import com.ikalimullin.base.employee.list.domain.model.EmployeeListAction
import com.ikalimullin.base.employee.list.presentation.list.EmployeeListViewModel
import com.ikalimullin.base.employee.list.presentation.list.EmployeeListViewState
import com.ikalimullin.base.employee.list.presentation.page.adapter.EmployeeListAdapter
import com.ikalimullin.core.coroutines.extensions.subscribeWithStartedState
import com.ikalimullin.core.stdlib.delegates.unsafeLazy
import com.ikalimullin.core.view.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
internal class EmployeePageFragment : Fragment(R.layout.fragment_employee_page) {

    companion object {
        fun newInstance() = EmployeePageFragment()
    }

    private val viewBinding by viewBinding(FragmentEmployeePageBinding::bind)

    private val viewModel by viewModels<EmployeeListViewModel>({ requireParentFragment() })

    private val screenAdapter by unsafeLazy {
        EmployeeListAdapter(
            refresh = { viewModel.action(EmployeeListAction.Refresh) },
            navigateToDetails = { id ->
                viewModel.action(EmployeeListAction.NavigateToDetails(id))
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeState()
    }

    private fun observeState() {
        viewModel.viewState
            .onEach(::handleState)
            .subscribeWithStartedState(viewLifecycleOwner)
    }

    private fun initView() = with(viewBinding) {
        employeePageRecyclerView.adapter = screenAdapter
        employeeSwipeRefreshLayout.setOnRefreshListener {
            viewModel.action(EmployeeListAction.Refresh)
        }
        employeeSwipeRefreshLayout.setColorSchemeResources(R.color.purple)
    }

    private fun handleState(state: EmployeeListViewState) = with(state) {
        screenAdapter.items = items
        viewBinding.employeeSwipeRefreshLayout.isRefreshing = isRefreshing
    }
}
