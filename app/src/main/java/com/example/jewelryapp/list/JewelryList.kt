package com.example.jewelryapp.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jewelryapp.data.JewelryRepository
import com.example.jewelryapp.data.dataClasses.Jewelry

import com.example.jewelryapp.R

//val jostFont = FontFamily(Font(R.font.jost_regular))

@Composable
fun JewelryList(
    viewModel: JewelryListViewModel = JewelryListViewModel(JewelryRepository()),
    onAddNewJewelryClick: () -> Unit,
    onJewelryClick: (String) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val jewelries by viewModel.jewelriesLiveData.observeAsState()

        if (!jewelries.isNullOrEmpty()) {
            LazyColumn(modifier = Modifier
                .fillMaxHeight()
                .padding(0.dp, 0.dp, 0.dp, 90.dp)) {
                items(items = jewelries!!.toList(), itemContent = { item ->
                    JewelryItem(jewelry = item, onJewelryClick)
                })
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            FloatingActionButton(
                modifier = Modifier,
                containerColor = colorResource(id = R.color.bleak_yellow_light),
                onClick = onAddNewJewelryClick
            ) {
                Text(
                    stringResource(id = R.string.btn_add_new_movie),
                    modifier = Modifier.padding(15.dp, 5.dp),
//                    fontFamily = jostFont,
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp
                )
            }

//            FloatingActionButton(
//                modifier = Modifier,
//                containerColor = colorResource(id = R.color.bleak_yellow_light),
//                onClick = onPlayerBtnClick
//            ) {
//                Text(
//                    stringResource(id = R.string.btn_go_player),
//                    modifier = Modifier.padding(15.dp, 5.dp),
////                    fontFamily = jostFont,
//                    color = colorResource(id = R.color.black),
//                    fontSize = 16.sp
//                )
//            }
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