package com.example.jewelryapp.navigation

sealed class Screens(val route: String) {
    object JewelryListScreen: Screens("jewelry_list")
}
