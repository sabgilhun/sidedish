package com.example.sidedish.data.datasource

import com.example.sidedish.data.dto.JwtDTO
import com.example.sidedish.data.dto.MenuDetailDTO
import com.example.sidedish.data.dto.MenuListDTO
import com.example.sidedish.model.OrderMenu
import retrofit2.Response


interface DataSource {

    suspend fun getMenuList(token: String, category: Int): Response<MenuListDTO>

    suspend fun getFoodDetail(token: String, id: Int): Response<MenuDetailDTO>

    suspend fun getJWT(code: String): Response<JwtDTO>

    suspend fun orderMenu(token: String, menu: OrderMenu): Response<Unit>
}