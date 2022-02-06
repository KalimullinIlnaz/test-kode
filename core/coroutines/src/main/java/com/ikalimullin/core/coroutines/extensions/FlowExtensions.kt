package com.ikalimullin.core.coroutines.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.emptyFlatMap() = flatMapLatest { emptyFlow<T>() }

fun <T, R> Flow<T>.mapWithDistinct(mapper: (T) -> R) =
    map(mapper::invoke).distinctUntilChanged()

inline fun <T> Flow<T>.withValue(
    scope: CoroutineScope,
    crossinline action: (T) -> Unit
) {
    scope.launch {
        this@withValue.first().also(action)
    }
}
