package com.example.sidedish.data.repository

import com.example.sidedish.model.Jwt

interface AuthRepository {

    suspend fun loadJwt(authenticationCode: String): Jwt
}