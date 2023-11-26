package com.example.jewelryapp.delete

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jewelryapp.data.JewelryRepository
import com.example.jewelryapp.data.dataClasses.Jewelry
import com.example.jewelryapp.data.network.MyResponse
import kotlinx.coroutines.launch

class DeleteViewModel(
    jewelryId: String,
    private val jewelryRepository: JewelryRepository
) : ViewModel() {

    val deleteResponseLiveData: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    fun deleteJewelryById(jewelryId: String) {
        viewModelScope.launch {
            if (!jewelryId.isNullOrEmpty()) {
                val response = jewelryRepository.deleteJewelry(jewelryId)
                deleteResponseLiveData.value = response
            }
        }
    }

}