package com.example.jewelryapp.data.network

import com.example.jewelryapp.data.network.jewelry.JewelryRequest
import com.example.jewelryapp.data.network.jewelry.JewelryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface JewelryService {
    @GET("records/all")
    suspend fun getAllJewelries(
        @Query("student_id") student_id: String
    ): MyListResponse<JewelryResponse>

    @POST("records")
    suspend fun insertNewJewelry(
        @Query("student_id") student_id: String,
        @Body jewelry: JewelryRequest
    ): MyResponse

    @GET("records/{record_id}")
    suspend fun getOneJewelryById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<JewelryResponse>

    @PUT("records/{record_id}")
    suspend fun updateJewelry(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String,
        @Body jewelry: JewelryRequest,
    ): MyItemResponse<JewelryResponse>
}