package com.example.jewelryapp.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jewelryapp.data.JewelryRepository
import com.example.jewelryapp.data.dataClasses.Jewelry

import com.example.jewelryapp.R

//val jostFont = FontFamily(Font(R.font.jost_regular))

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JewelryList(
    viewModel: JewelryListViewModel = JewelryListViewModel(JewelryRepository()),
    onMenuClicked: () -> Unit,
    onAddNewJewelryClick: () -> Unit,
    onJewelryClick: (String) -> Unit = {},
    navController: NavHostController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f)) // Spacer to push the title to center
                    Text(
                        text = "Luxury Store",
                        fontStyle = FontStyle.Italic,
                        color = Color.White,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Spacer to push the title to center
                }
            },
            navigationIcon = {
                IconButton(onClick = onMenuClicked) {
                    Icon(imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        modifier = Modifier.size(35.dp))
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorResource(id = R.color.primary_gold_color),
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White),
            actions = {
                // Button for adding new jewelry
                IconButton(onClick = onAddNewJewelryClick) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Jewelry",
                            modifier = Modifier.size(32.dp) // Custom size for add icon
                        )
                }
            }
        )

        Box() {
            val jewelries by viewModel.jewelriesLiveData.observeAsState()

            if (!jewelries.isNullOrEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(0.dp, 0.dp, 0.dp, 90.dp)
                ) {
                    items(items = jewelries!!.toList(), itemContent = { item ->
                        JewelryItem(jewelry = item, onJewelryClick)
                    })
                }
            }
        }
    }
}



@Composable
private fun JewelryItem(jewelry: Jewelry, onJewelryClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.bleak_yellow), //Card background color
            contentColor = Color.DarkGray  //Card content color,e.g.text
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    )
    {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    onJewelryClick(jewelry.id)
                }
        ) {
            JewelryItemTitle(jewelry.title)
            if (!jewelry.description.isNullOrEmpty())
                JewelryItemDescription(jewelry.description)
        }
    }
}

@Composable
private fun JewelryItemTitle(title: String) {
    Text(
        text = title,
        fontSize = 21.sp,
//        fontFamily = jostFont,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
private fun JewelryItemDescription(description: String) {
    Text(
        text = description,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = Color.Gray,
        fontSize = 18.sp,
//        fontFamily = jostFont,
        textAlign = TextAlign.Left
    )
}