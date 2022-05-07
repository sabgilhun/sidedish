package com.example.sidedish.network

import com.example.sidedish.data.dto.MenuListDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuListApi {

    @GET("/items/{categoryId}")
    suspend fun getMenuList(
        @Path("categoryId") category: Int
    ): MenuListDTO
}