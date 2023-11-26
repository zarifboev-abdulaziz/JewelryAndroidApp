package com.example.jewelryapp.data.network

import com.google.gson.annotations.SerializedName

class MyListResponse<T> (@SerializedName("data")
                         val data: List<T>?) : MyResponse()