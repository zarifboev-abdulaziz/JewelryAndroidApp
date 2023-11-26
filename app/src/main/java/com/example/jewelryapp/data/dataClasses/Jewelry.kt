package com.example.jewelryapp.data.dataClasses

data class Jewelry(
    val id: String = "",
    val title: String,
    val description: String?,
    val price: Double?,
    val size: Int?,
    val material: String?,
    val isCertified: Boolean?,
    val imageUrl: String?,
)
