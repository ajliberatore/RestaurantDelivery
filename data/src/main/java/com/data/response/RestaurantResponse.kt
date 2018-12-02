package com.data.response

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    val id: Int,
    val name: String,
    val description: String,
    @SerializedName("cover_img_url") val coverImgUrl: String,
    val status: String,
    @SerializedName("delivery_fee") val deliveryFee: Int
)
