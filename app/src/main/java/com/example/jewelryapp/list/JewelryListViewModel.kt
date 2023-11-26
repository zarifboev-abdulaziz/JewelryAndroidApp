package com.example.jewelryapp.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jewelryapp.data.JewelryRepository
import com.example.jewelryapp.data.dataClasses.Jewelry
import kotlinx.coroutines.launch

class JewelryListViewModel(
    private val jewelryRepository: JewelryRepository
) : ViewModel() {

    val jewelriesLiveData: MutableLiveData<List<Jewelry>> by lazy {
        MutableLiveData<List<Jewelry>>()
    }

    init {
        getAllJewelries()
    }

    fun getAllJewelries() {
        viewModelScope.launch {
            val jewelries = jewelryRepository.getJewelryList()
            jewelriesLiveData.value = jewelries
        }
    }
}