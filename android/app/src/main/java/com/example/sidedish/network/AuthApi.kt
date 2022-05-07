package com.example.sidedish.network

import com.example.sidedish.data.dto.JwtDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthApi {

    @GET("/login/oauth")
    suspend fun getJwt(
        @Query("code") authenticationCode: String
    ): JwtDTO
}