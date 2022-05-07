package com.example.sidedish.utils

fun Int.discount(discountRate: Int): Int {
    return this - (this / 100) * discountRate
}