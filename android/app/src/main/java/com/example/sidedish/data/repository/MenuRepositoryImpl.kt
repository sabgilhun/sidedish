package com.example.sidedish.data.repository

import com.example.sidedish.data.OrderMenu
import com.example.sidedish.data.datasource.MenuDataSource
import com.example.sidedish.data.dto.toMenuDetail
import com.example.sidedish.data.dto.toMenuList
import com.example.sidedish.model.MenuCategory
import com.example.sidedish.model.MenuDetail
import com.example.sidedish.model.MenuListItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuRepositoryImpl @Inject constructor(
    private val menuDataSource: MenuDataSource
) : MenuRepository {

    override suspend fun loadMenuList(
        category: MenuCategory
    ): List<MenuListItem> = menuDataSource.loadMenuList(category.id).toMenuList()

    override suspend fun loadMenuDetail(
        key: Int
    ): MenuDetail = menuDataSource.loadMenuDetail(key).toMenuDetail()

    override suspend fun orderMenu(
        orderMenu: OrderMenu
    ) = menuDataSource.orderMenu(orderMenu)
}