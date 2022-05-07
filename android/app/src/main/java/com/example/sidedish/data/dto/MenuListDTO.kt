package com.example.sidedish.data.dto


import com.example.sidedish.model.DiscountPolicy
import com.example.sidedish.model.MenuListItem
import com.example.sidedish.utils.discount
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

    menuList.add(MenuListItem.Category(categoryName))
    items.forEach { menuDto ->
        val id = requireNotNull(menuDto.id)
        val name = requireNotNull(menuDto.name)
        val desc = menuDto.description.orEmpty()
        val imageUrl = menuDto.mainImageLink.orEmpty()
        val price = requireNotNull(menuDto.price)
        val priceBeforeDiscount = menuDto.discountRate?.let { rate -> price.discount(rate) }
        val discountPolicy = DiscountPolicy.of(menuDto.discountPoilcy)

        val menu = MenuListItem.Menu(
            id = id,
            name = name,
            desc = desc,
            imageUrl = imageUrl,
            price = price,
            priceBeforeDiscount = priceBeforeDiscount,
            discountPolicy = discountPolicy
        )
        menuList.add(menu)
    }

    return menuList
}