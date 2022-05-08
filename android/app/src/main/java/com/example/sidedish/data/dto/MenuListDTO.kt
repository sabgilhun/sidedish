package com.example.sidedish.data.dto


import com.example.sidedish.common.discount
import com.example.sidedish.model.DiscountPolicy
import com.example.sidedish.model.MenuListItem


data class MenuListDTO(
    val categoryName: String?,
    val items: List<Item>?
) {
    data class Item(
        val description: String?,
        val discountPoilcy: String?,
        val discountRate: Int?,
        val id: Int?,
        val mainImageLink: String?,
        val name: String?,
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