package com.ikalimullin.core.mvi

import androidx.lifecycle.ViewModel

open class MviViewModel<STATE, ACTION>(private val useCase: UseCase<STATE, ACTION>) : ViewModel() {

    init {
        useCase.init()
    }

    val stateFlow = useCase.stateFlow

    fun action(action: ACTION) = useCase.action(action)

    override fun onCleared() {
        useCase.dispose()
        super.onCleared()
    }
}
