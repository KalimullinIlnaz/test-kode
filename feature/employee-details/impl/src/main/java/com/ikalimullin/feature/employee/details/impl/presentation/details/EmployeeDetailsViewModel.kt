package com.ikalimullin.feature.employee.details.impl.presentation.details

import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.back
import com.ikalimullin.core.mvi.MviViewModel
import com.ikalimullin.feature.employee.details.impl.domain.EmployeeDetailsAction
import com.ikalimullin.feature.employee.details.impl.domain.EmployeeDetailsState
import com.ikalimullin.feature.employee.details.impl.domain.EmployeeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class EmployeeDetailsViewModel @Inject constructor(
    useCase: EmployeeDetailsUseCase,
    private val modo: Modo
) : MviViewModel<EmployeeDetailsState, EmployeeDetailsAction>(useCase) {

    fun back() = modo.back()
}
