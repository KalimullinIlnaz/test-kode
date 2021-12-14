package com.ikalimullin.base.employee.list.presentation.list

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ikalimullin.base.employee.list.presentation.page.EmployeeItem
import com.ikalimullin.base.employee.list.presentation.page.EmployeePageFragment

internal class EmployeeListViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var employees = listOf<EmployeeItem>()

    override fun getItemCount() = employees.size

    override fun createFragment(position: Int) = EmployeePageFragment.newInstance()
}
