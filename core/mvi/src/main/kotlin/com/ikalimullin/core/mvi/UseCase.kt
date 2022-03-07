package com.ikalimullin.core.mvi

import kotlinx.coroutines.flow.Flow

interface UseCase<State, Action> : Store {
    val stateFlow: Flow<State>
    fun action(action: Action)
}
