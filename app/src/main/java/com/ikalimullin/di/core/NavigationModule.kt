package com.ikalimullin.di.core

import android.content.Context
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.MultiReducer
import com.github.terrakok.modo.android.AppReducer
import com.github.terrakok.modo.android.LogReducer
import com.ikalimullin.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NavigationModule {

    @Provides
    @Singleton
    fun provideModo(@ApplicationContext context: Context): Modo {
        val reducer = if (BuildConfig.DEBUG) {
            LogReducer(AppReducer(context, MultiReducer()))
        } else {
            AppReducer(context, MultiReducer())
        }
        return Modo(reducer)
    }
}
