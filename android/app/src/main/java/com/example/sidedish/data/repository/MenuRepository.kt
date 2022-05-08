package com.example.sidedish.data.repository

import com.example.sidedish.data.dto.MenuDetailDTO
import com.example.sidedish.model.MenuCategory
import com.example.sidedish.model.MenuDetail
import com.example.sidedish.model.MenuListItem

interface MenuRepository {

    suspend fun loadMenuList(category: MenuCategory): List<MenuListItem>

    suspend fun loadMenuDetail(key: Int): MenuDetail
}