package com.ikalimullin.base.employee.list.presentation

import com.ikalimullin.base.employee.list.domain.model.EmployeeListAction
import com.ikalimullin.base.employee.list.domain.model.EmployeeListInteractor
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.core.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class EmployeeListViewModel @Inject constructor(
    interactor: EmployeeListInteractor
) : MviViewModel<EmployeeListState, EmployeeListAction>(interactor)
