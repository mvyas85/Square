package com.challenge.square.di

import android.content.Context
import com.challenge.square.dao.EmployeeDatabase
import com.challenge.square.repository.EmployeeDirectoryRepository
import com.challenge.square.repository.EmployeeDirectoryRepositoryImpl
import com.challenge.square.service.api.EmployeeApi
import com.challenge.square.view.adapter.EmployeeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EmployeeDirectoryModule {

    @Provides
    @Singleton
    fun provideEmployeeDirectoryApi(retrofit: Retrofit): EmployeeApi {
        return retrofit.create(EmployeeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEmployeeDirectoryRepository(
            @ApplicationContext context: Context,
            api: EmployeeApi,
            database: EmployeeDatabase): EmployeeDirectoryRepository {
        return EmployeeDirectoryRepositoryImpl(context, api, database)
    }

    @Provides
    @Singleton
    fun provideEmployeeAdapter(): EmployeeAdapter {
        return EmployeeAdapter()
    }
}