package com.example.sidedish.di

import com.example.sidedish.data.datasource.DataSourceImpl
import com.example.sidedish.data.repository.Repository
import com.example.sidedish.network.ApiClient
import com.example.sidedish.network.AuthApi
import com.example.sidedish.network.MenuApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://13.209.145.70:8080/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun create(okHttpClient: OkHttpClient): ApiClient {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApi(okHttpClient: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMenuListApi(okHttpClient: OkHttpClient): MenuApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MenuApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMenuDataSource(apiClient: ApiClient): DataSourceImpl {
        return DataSourceImpl(apiClient)
    }

}


@Module
@InstallIn(SingletonComponent::class)
object RModule {

    @Singleton
    @Provides
    fun provideRepository(
        dataSourceImpl: DataSourceImpl
    ): Repository {
        return Repository(dataSourceImpl)
    }

}
