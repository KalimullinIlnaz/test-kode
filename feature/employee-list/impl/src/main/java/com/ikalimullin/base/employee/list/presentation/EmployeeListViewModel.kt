package com.ikalimullin.base.employee.list.presentation

import com.ikalimullin.base.employee.list.domain.model.EmployeeListAction
import com.ikalimullin.base.employee.list.domain.model.EmployeeListInteractor
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.core.coroutines.extensions.mapWithDistinct
import com.ikalimullin.core.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
internal class EmployeeListViewModel @Inject constructor(
    interactor: EmployeeListInteractor,
    viewStateMapper: EmployeeViewStateMapper,
    dispatchersProvider: DispatchersProvider
) : MviViewModel<EmployeeListState, EmployeeListAction>(interactor) {

    val viewState = stateFlow
        .mapWithDistinct(viewStateMapper)
        .flowOn(dispatchersProvider.default)
}
