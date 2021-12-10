package com.ikalimullin.core.coroutines

import kotlinx.coroutines.Dispatchers

private object DispatchersProviderImpl : DispatchersProvider {
    override val main = Dispatchers.Main.immediate
    override val io = Dispatchers.IO
    override val default = Dispatchers.Default
    override val unconfined = Dispatchers.Unconfined
}

fun provideDispatchersProvider(): DispatchersProvider = DispatchersProviderImpl
