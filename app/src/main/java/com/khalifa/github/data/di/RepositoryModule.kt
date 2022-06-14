package com.khalifa.github.data.di

import com.khalifa.github.data.remote.datasource.UserDetailsRemoteDataSourceImpl
import com.khalifa.github.data.remote.service.ApiService
import com.khalifa.github.data.repository.UsersRepositoryImpl
import com.khalifa.github.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providesUsersRepository(
        apiService: ApiService,
        userDetailsRemoteDataSourceImpl: UserDetailsRemoteDataSourceImpl
    ): UsersRepository =
        UsersRepositoryImpl(apiService, userDetailsRemoteDataSourceImpl)

    @Provides
    fun provideUserDetailsRemoteDataSource(apiService: ApiService): UserDetailsRemoteDataSourceImpl {
        return UserDetailsRemoteDataSourceImpl(apiService)
    }
}
