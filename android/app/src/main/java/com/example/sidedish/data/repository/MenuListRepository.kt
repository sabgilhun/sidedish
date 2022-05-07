package com.example.sidedish.data.repository

import com.example.sidedish.model.MenuCategory
import com.example.sidedish.model.MenuListItem

interface MenuListRepository {

    suspend fun loadMenuList(category: MenuCategory): List<MenuListItem>
}