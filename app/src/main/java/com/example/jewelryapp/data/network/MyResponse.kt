package com.example.jewelryapp.data.network

import com.google.gson.annotations.SerializedName

open class MyResponse () {
    @SerializedName("code")
    val code: String = ""
    @SerializedName("status")
    val status: String = ""
    @SerializedName("message")
    val message: String = ""
}