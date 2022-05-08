package com.example.sidedish.network

import com.example.sidedish.data.dto.MenuDetailDTO
import com.example.sidedish.data.dto.MenuListDTO
import com.example.sidedish.model.OrderMenu
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @POST("/order")
    suspend fun orderMenu(
        @Body body: OrderMenu
    )
}