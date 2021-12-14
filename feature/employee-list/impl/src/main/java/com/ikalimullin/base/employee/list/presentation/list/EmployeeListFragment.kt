package com.ikalimullin.base.employee.list.presentation.list

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.databinding.FragmentEmployeeListBinding
import com.ikalimullin.base.employee.list.domain.model.EmployeeListAction
import com.ikalimullin.base.employee.list.presentation.EmployeeListViewModel
import com.ikalimullin.base.employee.list.presentation.EmployeeListViewState
import com.ikalimullin.core.coroutines.extensions.subscribeWithStartedState
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

    private var viewPagerAdapter: EmployeeListViewPagerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = EmployeeListViewPagerAdapter(this)
        initView()

        viewModel.viewState
            .onEach(::handleState)
            .subscribeWithStartedState(viewLifecycleOwner)
    }

    override fun onDestroyView() {
        viewPagerAdapter = null
        viewBinding.employeeViewPager.adapter = null
        super.onDestroyView()
    }

    private fun handleState(state: EmployeeListViewState) = with(state) {
        viewPagerAdapter?.employees = items
    }

    private fun initView() = with(viewBinding) {
        searchView.sortIcon.setOnClickListener { viewModel.action(EmployeeListAction.Sort) }
        searchView.searchView.doOnTextChanged { text, _, _, _ ->
            viewModel.action(EmployeeListAction.Search(text?.toString().orEmpty()))
        }
        employeeViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(employeeTabLayout, employeeViewPager) { tab, _ ->
            viewModel.action(EmployeeListAction.TabSelected(tab.text?.toString().orEmpty()))
        }.attach()
        employeeTabLayout.addTab(employeeTabLayout.newTab().apply { text = "Все" })
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
}
