package com.example.sidedish.data.dto

import com.example.sidedish.common.discount
import com.example.sidedish.model.DiscountPolicy
import com.example.sidedish.model.MenuDetail

data class MenuDetailDTO(
    val id: Int?,
    val discountPolicy: String?,
    val discountRate: Int?,
    val description: String?,
    val name: String?,
    val price: Int?,
    val mainImageLink: String?,
    val detailImageLink: List<DetailImageLink>?
) {
    data class DetailImageLink(
        val id: Int?,
        val imageLinks: String?
    )
}

fun MenuDetailDTO.toMenuDetail(): MenuDetail {
    val id = requireNotNull(id)
    val name = requireNotNull(name)
    val desc = description.orEmpty()
    val originPrice = requireNotNull(price)
    val price = discountRate?.let { rate -> originPrice.discount(rate) } ?: originPrice
    val priceBeforeDiscount = if (discountRate == null) null else originPrice
    val discountPolicy = DiscountPolicy.of(discountPolicy)
    val imageUrl = mainImageLink.orEmpty()
    val detailImageLink = detailImageLink?.map { detailImageLink ->
        detailImageLink.imageLinks.orEmpty()
    }.orEmpty()

    return MenuDetail(
        id = id,
        name = name,
        desc = desc,
        price = price,
        priceBeforeDiscount = priceBeforeDiscount,
        discountPolicy = discountPolicy,
        imageUrl = imageUrl,
        detailImageLink = detailImageLink
    )
}