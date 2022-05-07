package com.example.sidedish.common

fun Int.discount(discountRate: Int): Int {
    return this - (this / 100) * discountRate
}