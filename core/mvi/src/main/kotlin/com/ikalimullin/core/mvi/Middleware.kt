package com.ikalimullin.core.mvi

import kotlinx.coroutines.flow.Flow

typealias Middleware<EFFECT, STATE> = (effects: Flow<EFFECT>, states: Flow<STATE>) -> Flow<EFFECT>
