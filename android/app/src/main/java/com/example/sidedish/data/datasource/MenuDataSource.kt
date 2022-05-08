package com.example.sidedish.data.datasource

import com.example.sidedish.data.dto.MenuDetailDTO
import com.example.sidedish.data.dto.MenuListDTO
import com.example.sidedish.model.OrderMenu

interface MenuDataSource {

    suspend fun loadMenuList(category: Int): MenuListDTO

    suspend fun loadMenuDetail(id: Int): MenuDetailDTO

    suspend fun orderMenu(orderMenu: OrderMenu)
}