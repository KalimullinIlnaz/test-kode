package com.ikalimullin.di.core

import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.core.coroutines.provideAppCoroutineScope
import com.ikalimullin.core.coroutines.provideDispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoreModule {

    @Provides
    @Singleton
    fun provideDispatchers() = provideDispatchersProvider()

    @Provides
    @Singleton
    fun provideApplicationCoroutineScope(
        dispatchersProvider: DispatchersProvider
    ) = provideAppCoroutineScope(dispatchersProvider)
}
