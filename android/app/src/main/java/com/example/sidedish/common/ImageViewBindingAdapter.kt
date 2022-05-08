package com.example.sidedish.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

private const val defaultImage =
    "https://leejohy-springboot-build.s3.ap-northeast-2.amazonaws.com/2cdf7235b65a114abff05b9133f90085.jpeg"

@BindingAdapter("imageUrl")
fun applyImageAdapter(view: ImageView, imageUrl: String?) {
    Glide.with(view)
        .load(imageUrl ?: defaultImage)
        .error(defaultImage)
        .into(view)
}