package com.ikalimullin.base.employee.list.presentation

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.databinding.FragmentEmployeeListBinding
import com.ikalimullin.base.employee.list.domain.model.EmployeeListAction
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.core.coroutines.extensions.subscribeWithStartedState
import com.ikalimullin.core.view.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    private val viewBinding by viewBinding(FragmentEmployeeListBinding::bind)

    private val viewModel by viewModels<EmployeeListViewModel>()

    private val screenAdapter by lazy {
        EmployeeListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.stateFlow
            .onEach(::handleState)
            .subscribeWithStartedState(viewLifecycleOwner)
    }

    private fun initView() = with(viewBinding) {
        searchView.sortIcon.setOnClickListener { viewModel.action(EmployeeListAction.Sort) }
        searchView.searchView.doOnTextChanged { text, _, _, _ ->
            viewModel.action(EmployeeListAction.Search(text?.toString().orEmpty()))
        }
    }

    private fun handleState(state: EmployeeListState) {
    }
}
