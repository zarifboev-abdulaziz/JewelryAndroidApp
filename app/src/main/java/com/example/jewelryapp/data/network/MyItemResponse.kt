package com.example.jewelryapp.data.network

import com.google.gson.annotations.SerializedName

class MyItemResponse<T> (@SerializedName("data")
                         val data: T?) : MyResponse()