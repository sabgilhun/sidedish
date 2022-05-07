package com.example.sidedish.data.repository

import com.example.sidedish.data.dto.JwtDTO
import com.example.sidedish.data.model.Jwt

interface AuthRepository {

    suspend fun loadJwt(authenticationCode: String): Jwt
}