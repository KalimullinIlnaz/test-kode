package com.ikalimullin.core.mvi

import kotlinx.coroutines.flow.Flow

interface Interactor<State, Action> : Store {
    val stateFlow: Flow<State>
    fun action(action: Action)
}