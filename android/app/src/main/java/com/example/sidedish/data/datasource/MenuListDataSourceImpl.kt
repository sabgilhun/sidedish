package com.example.sidedish.data.datasource

import com.example.sidedish.data.dto.MenuListDTO
import com.example.sidedish.network.MenuListApi
import javax.inject.Inject

class MenuListDataSourceImpl @Inject constructor(
    private val menuListApi: MenuListApi
) : MenuListDataSource {

    override suspend fun loadMenuList(
        category: Int
    ): MenuListDTO = menuListApi.getMenuList(category)
}