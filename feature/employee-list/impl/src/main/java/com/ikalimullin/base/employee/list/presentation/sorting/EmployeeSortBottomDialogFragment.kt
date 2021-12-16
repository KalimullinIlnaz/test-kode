package com.ikalimullin.base.employee.list.presentation.sorting

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ikalimullin.base.employee.list.R
import com.ikalimullin.base.employee.list.databinding.FragmentBottomEmployeeSortBinding
import com.ikalimullin.base.employee.list.domain.model.EmployeeListAction
import com.ikalimullin.base.employee.list.domain.model.SortingType
import com.ikalimullin.base.employee.list.presentation.EmployeeListViewModel
import com.ikalimullin.base.employee.list.presentation.EmployeeListViewState
import com.ikalimullin.core.coroutines.extensions.subscribeWithStartedState
import com.ikalimullin.core.view.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class EmployeeSortBottomDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = EmployeeSortBottomDialogFragment()
    }

    private val viewBinding by viewBinding(FragmentBottomEmployeeSortBinding::bind)

    private val viewModel by viewModels<EmployeeListViewModel>(({ requireParentFragment() }))

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        super.onCreateDialog(savedInstanceState).apply {
            (this as BottomSheetDialog).behavior.apply {
                skipCollapsed = true
                state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_bottom_employee_sort, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.updateLayoutParams<ViewGroup.LayoutParams> {
            height = Resources.getSystem().displayMetrics.heightPixels / 2
        }

        with(viewBinding) {
            alphabetically.setOnClickListener {
                viewModel.action(EmployeeListAction.Sorting.Set(SortingType.ALPHABETICALLY))
                dismiss()
            }
            byBirthday.setOnClickListener {
                viewModel.action(EmployeeListAction.Sorting.Set(SortingType.BIRTHDAY))
                dismiss()
            }
            byDefault.setOnClickListener {
                viewModel.action(EmployeeListAction.Sorting.Set(SortingType.DEFAULT))
                dismiss()
            }
        }

        viewModel.viewState
            .onEach(::handleState)
            .subscribeWithStartedState(viewLifecycleOwner)
    }

    private fun handleState(state: EmployeeListViewState) = with(state) {
        with(viewBinding) {
            alphabetically.isChecked = isAlphabeticallySortingChecked
            byBirthday.isChecked = isBirthdaySortingChecked
            byDefault.isChecked = isDefaultSortingChecked
        }
    }
}
