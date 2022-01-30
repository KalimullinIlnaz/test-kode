package com.ikalimullin.base.employee.list.presentation.list

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.databinding.FragmentEmployeeListBinding
import com.ikalimullin.base.employee.list.domain.model.EmployeeListAction
import com.ikalimullin.base.employee.list.presentation.EmployeeListViewModel
import com.ikalimullin.base.employee.list.presentation.EmployeeListViewState
import com.ikalimullin.base.employee.list.presentation.page.EmployeePageFragment
import com.ikalimullin.base.employee.list.presentation.sorting.EmployeeSortBottomDialogFragment
import com.ikalimullin.core.coroutines.extensions.subscribeWithStartedState
import com.ikalimullin.core.view.resourses.getCompatColor
import com.ikalimullin.core.view.viewBinding.viewBinding
import com.ikalimullin.entity.employee.Department
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    companion object {
        fun newInstance() = EmployeeListFragment()
    }

    private val viewBinding by viewBinding(FragmentEmployeeListBinding::bind)

    private val viewModel by viewModels<EmployeeListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEmployeePage()
        initView()
        observeState()
    }

    private fun initEmployeePage() = with(viewBinding) {
        val pageFragment = EmployeePageFragment.newInstance()
        childFragmentManager.commit { replace(pageFragmentContainer.id, pageFragment) }
    }

    private fun initView() = with(viewBinding) {
        initListeners()
        initTabs()
    }

    private fun initListeners() = with(viewBinding) {
        searchView.sortIcon.setOnClickListener {
            // Сделать абстракцию над модо или свой роутер для открытия диалогов
            /*viewModel.action(EmployeeListAction.Sorting.OpenScreen)*/
            EmployeeSortBottomDialogFragment.newInstance().show(childFragmentManager, "1")
        }
        searchView.searchView.doOnTextChanged { text, _, _, _ ->
            viewModel.action(EmployeeListAction.Search(text?.toString().orEmpty()))
        }
    }

    private fun initTabs() = with(viewBinding) {
        employeeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) =
                viewModel.action(EmployeeListAction.TabSelected(tab.text?.toString().orEmpty()))

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
        employeeTabLayout.addTab(
            employeeTabLayout.newTab().apply { text = getString(R.string.all) }
        )
        Department.values().forEach { department ->
            employeeTabLayout.addTab(employeeTabLayout.newTab().apply { text = department.name })
        }
        employeeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) =
                viewModel.action(EmployeeListAction.TabSelected(tab.text?.toString().orEmpty()))

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

    private fun observeState() {
        viewModel.viewState
            .onEach(::handleState)
            .subscribeWithStartedState(viewLifecycleOwner)
    }

    private fun handleState(state: EmployeeListViewState) = with(state) {
        viewBinding.searchView.sortIcon.imageTintList =
            ColorStateList.valueOf(requireContext().getCompatColor(sortIconColor))
    }
}
