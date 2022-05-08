package com.example.sidedish.network

import com.example.sidedish.data.OrderMenu
import com.example.sidedish.data.dto.MenuDetailDTO
import com.example.sidedish.data.dto.MenuListDTO
import retrofit2.Response
import retrofit2.http.*

interface MenuApi {

    @GET("/items/{categoryId}")
    suspend fun getMenuList(
        @Path("categoryId") category: Int
    ): MenuListDTO

    @GET("/items/detail/{id}")
    suspend fun getProductDetail(
        @Path("id") detailHash: Int
    ): MenuDetailDTO

    @POST("/order")
    suspend fun orderMenu(
        @Body body: OrderMenu
    )
}