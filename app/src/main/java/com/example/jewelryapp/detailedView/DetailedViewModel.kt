package com.example.jewelryapp.detailedView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jewelryapp.data.JewelryRepository
import com.example.jewelryapp.data.dataClasses.Jewelry
import kotlinx.coroutines.launch

class DetailedViewModel(
    jewelryId: String,
    private val jewelryRepository: JewelryRepository
) : ViewModel() {

    val jewelryLiveData: MutableLiveData<Jewelry> by lazy {
        MutableLiveData<Jewelry>()
    }

    init {
        getJewelryById(jewelryId)
    }

    private fun getJewelryById(jewelryId: String) {
        viewModelScope.launch {
            if (!jewelryId.isNullOrEmpty()) {
                val jewelry = jewelryRepository.getJewelryById(jewelryId)
                jewelryLiveData.value = jewelry
            }
        }
    }

}