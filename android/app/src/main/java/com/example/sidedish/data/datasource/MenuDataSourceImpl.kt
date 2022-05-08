package com.example.sidedish.data.datasource

import com.example.sidedish.data.OrderMenu
import com.example.sidedish.data.dto.MenuDetailDTO
import com.example.sidedish.data.dto.MenuListDTO
import com.example.sidedish.network.MenuApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuDataSourceImpl @Inject constructor(
    private val menuApi: MenuApi
) : MenuDataSource {

    override suspend fun loadMenuList(
        category: Int
    ): MenuListDTO = menuApi.getMenuList(category)

    override suspend fun loadMenuDetail(
        id: Int
    ): MenuDetailDTO = menuApi.getProductDetail(id)

    override suspend fun orderMenu(
        orderMenu: OrderMenu
    ) = menuApi.orderMenu(orderMenu)
}