package com.example.sidedish.network

import com.example.sidedish.data.dto.MenuDetailDTO
import com.example.sidedish.data.dto.MenuListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MenuApi {

    @GET("/items/{categoryId}")
    suspend fun getMenuList(
        @Path("categoryId") category: Int
    ): MenuListDTO

    @GET("/items/detail/{id}")
    suspend fun getProductDetail(
        @Path("id") detailHash: Int
    ): MenuDetailDTO
}