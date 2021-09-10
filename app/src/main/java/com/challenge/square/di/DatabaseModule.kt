package com.challenge.square.di

import android.content.Context
import androidx.room.Room
import com.challenge.square.dao.EmployeeDao
import com.challenge.square.dao.EmployeeDatabase
import com.challenge.square.dao.EmployeeLocalDataSource
import com.challenge.square.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideEmployeeLocalDataSource(employeeDao: EmployeeDao) = EmployeeLocalDataSource(employeeDao)

    @Singleton
    @Provides
    fun provideEmployeeDatabase(
            @ApplicationContext context: Context) = Room.databaseBuilder(context,
            EmployeeDatabase::class.java, DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun provideEmployeeDao(database: EmployeeDatabase) = database.employeeDao()

}