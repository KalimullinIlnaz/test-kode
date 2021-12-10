package com.ikalimullin.core.coroutines.extensions

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.emptyFlatMap() = flatMapLatest { emptyFlow<T>() }
