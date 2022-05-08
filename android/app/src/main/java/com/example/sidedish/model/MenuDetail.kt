package com.example.sidedish.model

data class MenuDetail(
    val id: Int,
    val name: String,
    val desc: String,
    val price: Int,
    val priceBeforeDiscount: Int?,
    val discountPolicy: DiscountPolicy?,
    val imageUrl: String,
    val detailImageLink: List<String>
)