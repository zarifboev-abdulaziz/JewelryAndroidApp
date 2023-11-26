package com.example.jewelryapp.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jewelryapp.addNew.AddNewActivity
import com.example.jewelryapp.detailedView.DetailedView
import com.example.jewelryapp.list.JewelryList

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = Screens.JewelryListScreen.route) {
        composable(Screens.JewelryListScreen.route) {
            JewelryList(onAddNewJewelryClick = {
                context.startActivity(Intent(context, AddNewActivity::class.java))
            }, onJewelryClick = { jewelryId ->
                navController.navigate("detailedView/$jewelryId")
            }
            )
        }

        composable(
            route = "detailedView/{jewelryId}"
        ) { backStackEntry ->
            DetailedView(jewelryId = backStackEntry.arguments?.getString("jewelryId")!!)
        }
    }
}