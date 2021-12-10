package com.ikalimullin.base.employee.list.di

import com.ikalimullin.base.employee.list.data.EmployeeNetworkApi
import com.ikalimullin.base.employee.list.data.repository.EmployeeNetworkRepositoryImpl
import com.ikalimullin.base.employee.list.domain.repository.EmployeeNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object EmployeeNetworkDiModule {

    @Singleton
    @Provides
    fun provideEmployeeNetworkApi(retrofit: Retrofit): EmployeeNetworkApi =
        retrofit.create(EmployeeNetworkApi::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface Repository {

        @Binds
        fun bindEmployeeNetworkRepository(
            repository: EmployeeNetworkRepositoryImpl
        ): EmployeeNetworkRepository
    }
}
