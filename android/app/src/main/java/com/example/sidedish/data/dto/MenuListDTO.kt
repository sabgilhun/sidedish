package com.example.sidedish.data.dto


import com.example.sidedish.model.MenuListItem
import com.google.gson.annotations.SerializedName


data class MenuListDTO(
    @SerializedName("categoryName")
    val categoryName: String?,
    @SerializedName("items")
    val items: List<Item>?
) {
    data class Item(
        @SerializedName("description")
        val description: String?,
        @SerializedName("discountPoilcy")
        val discountPoilcy: String?,
        @SerializedName("discountRate")
        val discountRate: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("mainImageLink")
        val mainImageLink: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("price")
        val price: Int?
    )
}

fun MenuListDTO.toMenuList(): List<MenuListItem> {
    val categoryName = requireNotNull(categoryName)
    val items = requireNotNull(items)

    val menuList = mutableListOf<MenuListItem>()

    // TODO
    menuList.add(MenuListItem.Category(categoryName))
    items.forEach {
        menuList.add(MenuListItem.Menu(""))
    }

    return menuList
}