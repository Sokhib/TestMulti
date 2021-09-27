package com.example.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.common.R

@BindingAdapter("url")
fun loadImage(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }
    Glide.with(imageView.context)
        .load(url)
        .centerInside()
//        .diskCacheStrategy(DiskCacheStrategy.NONE)
//        .skipMemoryCache(true)
        .placeholder(R.drawable.ic_article)
        .into(imageView)
}