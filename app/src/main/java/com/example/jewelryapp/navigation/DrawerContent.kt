package com.example.jewelryapp.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jewelryapp.R
import com.example.jewelryapp.addNew.AddNewActivity


@Composable
fun DrawerContent(onDrawerItemClicked: () -> Unit, navController: NavHostController, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row() {
            IconButton(onClick = {
                onDrawerItemClicked()
                navController.navigate(Screens.JewelryListScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = "Close Menu",
                    modifier = Modifier.size(35.dp),
                    tint = colorResource(id = R.color.primary_gold_color)
                )
            }
            Spacer(modifier = Modifier.weight(0.8f)) // Spacer to push the title to center
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Menu",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.weight(1.2f)) // Spacer to push the title to center
        }

        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(text = "Profile", onClick = {
            onDrawerItemClicked()
            navController.navigate(Screens.JewelryListScreen.route)
        })
        GoldLine()
        DrawerItem(text = "My advertisements", onClick = {
            onDrawerItemClicked()
            navController.navigate(Screens.JewelryListScreen.route)
        })
        GoldLine()
        DrawerItem(text = "Add advertisement", onClick = {
            onDrawerItemClicked()
            context.startActivity(Intent(context, AddNewActivity::class.java))
        })
    }
}

@Composable
fun DrawerItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp),
        fontSize = 20.sp
    )
}

@Composable
fun GoldLine() {
    Box(
        modifier = Modifier
//            .fillMaxWidth()
            .width(330.dp)
            .height(1.dp)
            .background(color = colorResource(id = R.color.primary_gold_color))
    )
}