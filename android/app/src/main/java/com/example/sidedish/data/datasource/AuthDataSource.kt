package com.example.sidedish.data.datasource

import com.example.sidedish.data.dto.JwtDTO

interface AuthDataSource {

    suspend fun loadJwt(authenticationCode: String): JwtDTO
}