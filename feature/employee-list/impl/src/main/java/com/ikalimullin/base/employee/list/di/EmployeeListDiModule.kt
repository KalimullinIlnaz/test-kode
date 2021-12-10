package com.ikalimullin.base.employee.list.di

import com.ikalimullin.base.employee.list.domain.model.EmployeeListInteractor
import com.ikalimullin.base.employee.list.domain.model.EmployeeListMiddleware
import com.ikalimullin.base.employee.list.domain.model.EmployeeListModel
import com.ikalimullin.base.employee.list.domain.model.middlewares.LoadEmployeeMiddleware
import com.ikalimullin.core.coroutines.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoSet
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.SOURCE)
internal annotation class EmployeeListScope

@EmployeeListScope
@DefineComponent(parent = FragmentComponent::class)
internal interface EmployeeListComponent

@Module
@InstallIn(EmployeeListComponent::class)
internal object EmployeeListDIModule {

    @EmployeeListScope
    @Provides
    fun provideEmployeeListModel(
        dispatchersProvider: DispatchersProvider,
        middlewares: Set<EmployeeListMiddleware>
    ) : EmployeeListInteractor = EmployeeListModel(dispatchersProvider, middlewares)

    @Module
    @InstallIn(EmployeeListComponent::class)
    interface Middlewares {

        @Binds
        @EmployeeListScope
        @IntoSet
        fun bindCodeLoadEmployeeMiddleware(
            middleware: LoadEmployeeMiddleware
        ): EmployeeListMiddleware
    }
}
