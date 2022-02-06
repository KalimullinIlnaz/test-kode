package com.ikalimullin.base.employee.list.presentation.list

import androidx.lifecycle.viewModelScope
import com.ikalimullin.base.employee.list.domain.model.EmployeeListAction
import com.ikalimullin.base.employee.list.domain.model.EmployeeListInteractor
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.core.coroutines.extensions.mapWithDistinct
import com.ikalimullin.core.coroutines.extensions.withValue
import com.ikalimullin.core.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
internal class EmployeeListViewModel @Inject constructor(
    interactor: EmployeeListInteractor,
    viewStateMapper: EmployeeViewStateMapper,
    dispatchersProvider: DispatchersProvider
) : MviViewModel<EmployeeListState, EmployeeListAction>(interactor) {

    val viewState = stateFlow
        .mapWithDistinct(viewStateMapper)
        .flowOn(dispatchersProvider.default)

    fun selectTab(tab: String) = stateFlow.withValue(viewModelScope) { state ->
        action(EmployeeListAction.TabSelected(tab))
        action(EmployeeListAction.Sorting.Set(state.sortingType))
    }
}
