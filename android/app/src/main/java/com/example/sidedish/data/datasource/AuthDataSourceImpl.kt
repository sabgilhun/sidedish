package com.example.sidedish.data.datasource

import com.example.sidedish.network.AuthApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthDataSource {

    override suspend fun loadJwt(authenticationCode: String) = authApi.getJwt(authenticationCode)
}