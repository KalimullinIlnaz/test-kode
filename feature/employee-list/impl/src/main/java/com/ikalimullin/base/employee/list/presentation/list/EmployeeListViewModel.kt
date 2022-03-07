package com.ikalimullin.base.employee.list.presentation.list

import androidx.lifecycle.viewModelScope
import com.ikalimullin.base.employee.list.domain.model.EmployeeListAction
import com.ikalimullin.base.employee.list.domain.model.EmployeeListState
import com.ikalimullin.base.employee.list.domain.model.EmployeeListUseCase
import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.core.coroutines.extensions.mapWithDistinct
import com.ikalimullin.core.coroutines.extensions.withValue
import com.ikalimullin.core.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
internal class EmployeeListViewModel @Inject constructor(
    useCase: EmployeeListUseCase,
    viewStateMapper: EmployeeViewStateMapper,
    dispatchersProvider: DispatchersProvider
) : MviViewModel<EmployeeListState, EmployeeListAction>(useCase) {

    val viewState = stateFlow
        .mapWithDistinct(viewStateMapper)
        .flowOn(dispatchersProvider.default)

    fun selectTab(tab: String) = stateFlow.withValue(viewModelScope) { state ->
        action(EmployeeListAction.TabSelected(tab))
        action(EmployeeListAction.Sorting.Set(state.sortingType))
    }
}
