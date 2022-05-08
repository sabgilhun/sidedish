package com.example.sidedish.data.dto

import com.example.sidedish.common.discount
import com.example.sidedish.data.DetailImageLinks
import com.example.sidedish.data.Menu
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

fun MenuDetailDTO.toMenuDetail(): MenuDetail = MenuDetail(
    id = requireNotNull(id),
    name = requireNotNull(name),
    desc = description.orEmpty(),
    price = requireNotNull(price),
    priceBeforeDiscount = discountRate?.let { rate -> price.discount(rate) },
    discountPolicy = DiscountPolicy.of(discountPolicy),
    imageUrl = mainImageLink.orEmpty(),
    detailImageLink = detailImageLink?.map { detailImageLink ->
        detailImageLink.imageLinks.orEmpty()
    }.orEmpty()
)

fun MenuDetailDTO.inputDTOToMenu(): Menu = Menu(
    description,
    discountPolicy,
    discountRate,
    id,
    mainImageLink,
    name,
    price,
    detailImageLink = makeDetailImageLinkList(detailImageLink)
)

fun makeDetailImageLinkList(list: List<MenuDetailDTO.DetailImageLink>?): List<DetailImageLinks> {
    val newList = mutableListOf<DetailImageLinks>()
    list?.forEach { detailImageLink ->
        newList.add(DetailImageLinks(detailImageLink.id, detailImageLink.imageLinks))
    }
    return newList
}