package com.example.jewelryapp.edit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jewelryapp.data.JewelryRepository
import com.example.jewelryapp.data.dataClasses.Jewelry
import com.example.jewelryapp.data.network.MyResponse
import kotlinx.coroutines.launch

class EditJewelryViewModel(
    jewelryId: String,
    private val jewelryRepository: JewelryRepository
) : ViewModel() {
    val jewelryLiveData: MutableLiveData<Jewelry> by lazy {
        MutableLiveData<Jewelry>()
    }
    val updateResponseLiveData: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    init {
        getJewelry(jewelryId)
    }

    private fun getJewelry(jewelryId: String) {
        viewModelScope.launch {
            val jewelry = jewelryRepository.getJewelryById(jewelryId)
            jewelryLiveData.value = jewelry
        }
    }

    fun updateJewelry(jewelry: Jewelry) {
        viewModelScope.launch {
            try {
                val response = jewelryRepository.updateJewelry(jewelry)
                updateResponseLiveData.value = response

                Log.d("Update response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}