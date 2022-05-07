package com.example.sidedish.data.datasource

import com.example.sidedish.data.dto.MenuListDTO
import retrofit2.Response

interface MenuListDataSource {

    suspend fun loadMenuList(category: Int): MenuListDTO
}