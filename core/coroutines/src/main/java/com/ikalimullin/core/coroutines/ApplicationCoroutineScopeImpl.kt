package com.ikalimullin.core.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

private class ApplicationCoroutineScopeImpl(
    dispatchersProvider: DispatchersProvider
) : AppCoroutineScope {

    override val coroutineContext =
        SupervisorJob() +
            dispatchersProvider.unconfined +
            CoroutineExceptionHandler { _, throwable -> Timber.e(throwable) } +
            CoroutineName(AppCoroutineScope::class.java.simpleName)
}

fun provideAppCoroutineScope(
    dispatchersProvider: DispatchersProvider
): AppCoroutineScope = ApplicationCoroutineScopeImpl(dispatchersProvider)
