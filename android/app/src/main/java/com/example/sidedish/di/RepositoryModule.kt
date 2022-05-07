package com.example.sidedish.di

import com.example.sidedish.data.repository.AuthRepository
import com.example.sidedish.data.repository.AuthRepositoryImpl
import com.example.sidedish.data.repository.MenuListRepository
import com.example.sidedish.data.repository.MenuListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun bindMenuListRepository(
        menuListRepositoryImpl: MenuListRepositoryImpl
    ): MenuListRepository
}