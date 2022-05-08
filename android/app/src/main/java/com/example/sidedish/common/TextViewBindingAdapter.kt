package com.example.sidedish.common

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.sidedish.R
import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("#,###")

@BindingAdapter("priceAmount")
fun applyPriceFormat(textView: TextView, price: Int?) {
    if (price != null) {
        textView.text = textView.context.getString(R.string.currency, decimalFormat.format(price))
    }
}

@BindingAdapter("strike")
fun applyStrike(textView: TextView, useStrike: Boolean?) {
    val originPaintFlags = textView.paintFlags
    if (useStrike == true) {
        textView.paintFlags = originPaintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = originPaintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}