package com.example.sidedish.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.sidedish.R

enum class DiscountPolicy(
    val title: String,
    @DrawableRes val backgroundRes: Int,
    @ColorRes val textColorRes: Int
) {
    LAUNCHING(
        title = "런칭특가",
        backgroundRes = R.drawable.background_badge_event,
        textColorRes = R.color.purple_500
    ),

    EVENT(
        title = "이벤트특가",
        backgroundRes = R.drawable.background_badge_limited,
        textColorRes = R.color.white
    );


    companion object {
        fun of(
            policyStr: String?
        ): DiscountPolicy? = when (policyStr) {
            "런칭특가" -> LAUNCHING
            "이벤트특가" -> EVENT
            else -> null
        }
    }
}
