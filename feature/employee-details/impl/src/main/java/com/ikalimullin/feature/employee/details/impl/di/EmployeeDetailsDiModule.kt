package com.ikalimullin.feature.employee.details.impl.di

import com.ikalimullin.core.coroutines.DispatchersProvider
import com.ikalimullin.feature.employee.details.impl.domain.EmployeeDetailsInteractor
import com.ikalimullin.feature.employee.details.impl.domain.EmployeeDetailsModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal class EmployeeDetailsDiModule {

    @ViewModelScoped
    @Provides
    fun provideEmployeeListModel(
        dispatchersProvider: DispatchersProvider
    ): EmployeeDetailsInteractor = EmployeeDetailsModel(dispatchersProvider)
}
