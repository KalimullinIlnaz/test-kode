package com.ikalimullin.core.mvi

typealias Reducer<Effect, State> = (effect: Effect, state: State) -> State
