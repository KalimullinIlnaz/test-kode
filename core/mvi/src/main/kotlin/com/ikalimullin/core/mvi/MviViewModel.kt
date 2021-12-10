package com.ikalimullin.core.mvi

import androidx.lifecycle.ViewModel

open class MviViewModel<STATE, ACTION>(private val interactor: Interactor<STATE, ACTION>) : ViewModel() {

    init {
        interactor.init()
    }

    val stateFlow = interactor.stateFlow

    fun action(action: ACTION) = interactor.action(action)

    override fun onCleared() {
        interactor.dispose()
        super.onCleared()
    }
}