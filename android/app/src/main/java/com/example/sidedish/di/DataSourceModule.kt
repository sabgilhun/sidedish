package com.example.sidedish.di

import com.example.sidedish.data.datasource.AuthDataSource
import com.example.sidedish.data.datasource.AuthDataSourceImpl
import com.example.sidedish.data.datasource.MenuDataSource
import com.example.sidedish.data.datasource.MenuDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Singleton
    @Binds
    abstract fun bindMenuListDataSource(
        menuListDataSourceImpl: MenuDataSourceImpl
    ): MenuDataSource
}