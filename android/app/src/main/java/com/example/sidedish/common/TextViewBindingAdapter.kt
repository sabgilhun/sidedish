package com.example.sidedish.common

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