package com.example.jewelryapp.navigation

import android.content.Context
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jewelryapp.addNew.AddNewActivity
import com.example.jewelryapp.data.JewelryRepository
import com.example.jewelryapp.detailedView.DetailedView
import com.example.jewelryapp.edit.EditJewelry
import com.example.jewelryapp.list.JewelryList
import com.example.jewelryapp.navigation.Navigation
import com.example.jewelryapp.navigation.Screens
import com.example.jewelryapp.ui.theme.JewelryAppTheme

import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavHostController, context: Context) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(onDrawerItemClicked = {
                scope.launch {
                    drawerState.close()
                }
            }, navController = navController, context = context)
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = Screens.JewelryListScreen.route
            ) {
                composable(Screens.JewelryListScreen.route) {
                    JewelryList(onMenuClicked = {
                        navController.navigate("drawerContent")
                    }, onAddNewJewelryClick =  {
                        context.startActivity(Intent(context, AddNewActivity::class.java))
                    }, onJewelryClick = { jewelryId ->
                        navController.navigate("detailedView/$jewelryId")
                    }, onEditJewelryClick = { jewelryId ->
                        navController.navigate("edit/$jewelryId")
                    }
                    )
                }

                composable("drawerContent") {
                    DrawerContent(onDrawerItemClicked = {
                        scope.launch {
                            drawerState.close()
                        }
                    }, navController = navController, context = context)
                }

                composable(
                    route = "detailedView/{jewelryId}"
                ) { backStackEntry ->
                    DetailedView(jewelryId = backStackEntry.arguments?.getString("jewelryId")!!)
                }

                composable(
                    route = "edit/{jewelryId}"
                ) { backStackEntry ->
                    EditJewelry(jewelryId = backStackEntry.arguments?.getString("jewelryId")!!)
                }
            }
        }
    )

}
