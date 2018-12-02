package com.example.anthonyliberatore.restaurantdelivery.app.databinding

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * BindingAdapters.kt contains Databinding conversions and adapters
 */

@BindingConversion
fun setVisibility(state: Boolean): Int {
  return if (state) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
  if (url != null) {
    Picasso.get().load(url).into(imageView)
  }
}
