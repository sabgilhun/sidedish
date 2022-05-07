package com.example.sidedish.data.repository

import com.example.sidedish.data.datasource.AuthDataSource
import com.example.sidedish.data.dto.toJwt
import com.example.sidedish.data.model.Jwt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun loadJwt(
        authenticationCode: String
    ): Jwt = authDataSource.loadJwt(authenticationCode).toJwt()
}