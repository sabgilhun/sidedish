package com.example.sidedish.data.repository

import com.example.sidedish.data.AppSession
import com.example.sidedish.data.datasource.AuthDataSource
import com.example.sidedish.data.dto.toJwt
import com.example.sidedish.model.Jwt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val appSession: AppSession
) : AuthRepository {

    override suspend fun loadJwt(
        authenticationCode: String
    ): Jwt = authDataSource.loadJwt(authenticationCode).toJwt()

    override fun saveJwt(jwt: Jwt) {
        appSession.jwt = jwt
    }
}