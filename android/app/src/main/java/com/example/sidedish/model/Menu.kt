package com.example.sidedish.model

sealed class MenuListItem {

    data class Category(
        val categoryName: String
    ) : MenuListItem()

    data class Menu(
        val tmp: String
    ) : MenuListItem()
}