package com.example.jewelryapp.data.network.jewelry

import com.google.gson.annotations.SerializedName

data class JewelryResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("type")
    val material: String?,
    @SerializedName("is_it_true")
    val isCertified: String?,
    @SerializedName("url")
    val imageUrl: String?,
)