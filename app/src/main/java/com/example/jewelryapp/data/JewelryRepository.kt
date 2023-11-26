package com.example.jewelryapp.data

import android.util.Log
import com.example.jewelryapp.data.dataClasses.Jewelry
import com.example.jewelryapp.data.network.MyItemResponse
import com.example.jewelryapp.data.network.MyListResponse
import com.example.jewelryapp.data.network.MyResponse
import com.example.jewelryapp.data.network.RetrofitInstance
import com.example.jewelryapp.data.network.jewelry.JewelryRequest
import com.example.jewelryapp.data.network.jewelry.JewelryResponse

const val STUDENT_ID = "00011224cw"

class JewelryRepository {
    suspend fun getJewelryList(): List<Jewelry> {
        val jewelries = mutableListOf<Jewelry>()

        try {
            val response: MyListResponse<JewelryResponse> =
                RetrofitInstance.jewelryService.getAllJewelries(STUDENT_ID)
            val jewelriesFromResponse = response.data

            if (jewelriesFromResponse != null) {
                for (jewelryFromResponse in jewelriesFromResponse) {
                    jewelries.add(
                        Jewelry(
                            id = jewelryFromResponse.id.toString(),
                            title = jewelryFromResponse.title,
                            description = jewelryFromResponse.description,
                            price = jewelryFromResponse.price,
                            size = jewelryFromResponse.size,
                            material = jewelryFromResponse.material,
                            isCertified = jewelryFromResponse.isCertified,
                            imageUrl = jewelryFromResponse.imageUrl
                        )
                    )
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return jewelries
    }

    suspend fun insertNewJewelry(jewelry: Jewelry): MyResponse? {
        val response: MyResponse

        try {
            val jewelryRequest =
                JewelryRequest(
                    title = jewelry.title,
                    description = jewelry.description,
                    price = jewelry.price,
                    size = jewelry.size,
                    material = jewelry.material,
                    isCertified = jewelry.isCertified,
                    imageUrl = jewelry.imageUrl
                )

            response = RetrofitInstance.jewelryService.insertNewJewelry(
                STUDENT_ID,
                jewelryRequest
            )

            Log.d("Update_response", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return response
    }

    suspend fun getJewelryById(jewelryId: String): Jewelry? {
        try {
            val response: MyItemResponse<JewelryResponse> =
                RetrofitInstance.jewelryService.getOneJewelryById(jewelryId, STUDENT_ID)
            val jewelryFromResponse = response.data

            if (jewelryFromResponse != null) {
                return Jewelry(
                    id = jewelryFromResponse.id.toString(),
                    title = jewelryFromResponse.title,
                    description = jewelryFromResponse.description,
                    price = jewelryFromResponse.price,
                    size = jewelryFromResponse.size,
                    material = jewelryFromResponse.material,
                    isCertified = jewelryFromResponse.isCertified,
                    imageUrl = jewelryFromResponse.imageUrl
                )
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }
}

