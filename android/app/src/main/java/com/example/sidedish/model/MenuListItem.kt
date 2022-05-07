package com.example.sidedish.model

sealed class MenuListItem {

    data class Category(
        val categoryName: String
    ) : MenuListItem()

    data class Menu(
        val id: Int,
        val name: String,
        val desc: String,
        val imageUrl: String,
        val price: Int,
        val priceBeforeDiscount: Int?,
        val discountPolicy: DiscountPolicy?
    ) : MenuListItem()
}