package com.example.jewelryapp.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jewelryapp.data.JewelryRepository
import com.example.jewelryapp.data.dataClasses.Jewelry
import com.example.jewelryapp.data.network.MyResponse

import kotlinx.coroutines.launch

class AddNewJewelryViewModel(private val jewelryRepository: JewelryRepository) : ViewModel() {

    val insertResponseLiveData: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    fun saveNewJewelry(jewelry: Jewelry) {
        viewModelScope.launch {
            try {

                val response = jewelryRepository.insertNewJewelry(jewelry)
                insertResponseLiveData.value = response

                Log.d("Update_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}