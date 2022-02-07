package com.ikalimullin.base.employee.list.di

import com.ikalimullin.base.employee.list.domain.model.EmployeeListInteractor
import com.ikalimullin.base.employee.list.domain.model.EmployeeListMiddleware
import com.ikalimullin.base.employee.list.domain.model.EmployeeListModel
import com.ikalimullin.base.employee.list.domain.model.middlewares.LoadEmployeeMiddleware
import com.ikalimullin.base.employee.list.domain.model.middlewares.NavigateToEmployeeDetailsMiddleware
import com.ikalimullin.base.employee.list.presentation.list.EmployeeListStateToViewStateMapper
import com.ikalimullin.base.employee.list.presentation.list.EmployeeViewStateMapper
import com.ikalimullin.core.coroutines.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

@Module
@InstallIn(ViewModelComponent::class)
internal object EmployeeListDiModule {

    @ViewModelScoped
    @Provides
    fun provideEmployeeListModel(
        dispatchersProvider: DispatchersProvider,
        middlewares: @JvmSuppressWildcards Set<EmployeeListMiddleware>
    ): EmployeeListInteractor = EmployeeListModel(dispatchersProvider, middlewares)

    @Module
    @InstallIn(ViewModelComponent::class)
    interface Middlewares {

        @Binds
        @ViewModelScoped
        @IntoSet
        fun bindNavigateToEmployeeDetailsMiddleware(
            middleware: NavigateToEmployeeDetailsMiddleware
        ): EmployeeListMiddleware

        @Binds
        @ViewModelScoped
        @IntoSet
        fun bindLoadEmployeeMiddleware(
            middleware: LoadEmployeeMiddleware
        ): EmployeeListMiddleware
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    interface ViewModel {

        @Binds
        @ViewModelScoped
        fun bindEmployeeViewStateMapper(
            viewStateMapper: EmployeeListStateToViewStateMapper
        ): EmployeeViewStateMapper
    }
}
