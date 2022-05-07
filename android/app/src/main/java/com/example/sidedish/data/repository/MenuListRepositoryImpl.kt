package com.example.sidedish.data.repository

import com.example.sidedish.data.datasource.MenuListDataSource
import com.example.sidedish.data.dto.toMenuList
import com.example.sidedish.model.MenuCategory
import com.example.sidedish.model.MenuListItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuListRepositoryImpl @Inject constructor(
    private val menuListDataSource: MenuListDataSource
) : MenuListRepository {

    override suspend fun loadMenuList(
        category: MenuCategory
    ): List<MenuListItem> = menuListDataSource.loadMenuList(category.id).toMenuList()
}