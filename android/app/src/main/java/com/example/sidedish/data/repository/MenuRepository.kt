package com.example.sidedish.data.repository

import com.example.sidedish.model.MenuCategory
import com.example.sidedish.model.MenuDetail
import com.example.sidedish.model.MenuListItem
import com.example.sidedish.model.OrderMenu

interface MenuRepository {

    suspend fun loadMenuList(category: MenuCategory): List<MenuListItem>

    suspend fun loadMenuDetail(key: Int): MenuDetail

    suspend fun orderMenu(orderMenu: OrderMenu)
}