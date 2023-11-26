package com.example.jewelryapp.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
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
    onEditJewelryClick: (String) -> Unit = {}
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
                        JewelryItem(jewelry = item, onJewelryClick, onEditJewelryClick)
                    })
                }
            }
        }
    }
}



@Composable
private fun JewelryItem(jewelry: Jewelry, onJewelryClick: (String) -> Unit, onEditJewelryClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.DarkGray  //Card content color,e.g.text
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    onJewelryClick(jewelry.id)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                modifier = Modifier.size(width = 170.dp, height = 120.dp),
                model = jewelry.imageUrl,
                contentDescription = "Jewelry image",
                placeholder = painterResource(id = R.drawable.default_jewelry_img),
                error = painterResource(id = R.drawable.default_jewelry_img),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))

//            JewelryItemTitle(jewelry.title)
//            if (!jewelry.description.isNullOrEmpty())
//                JewelryItemDescription(jewelry.description)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                JewelryItemTitle(jewelry.title)
                JewelryItemPrice(jewelry.price.toString())
                JewelryItemMaterial(jewelry.material!!, jewelry.isCertified!!)
                JewelryItemActions(jewelry.id, onEditJewelryClick)
            }
        }
    }
}

@Composable
private fun JewelryItemTitle(title: String) {
    Text(
        text = title,
        fontSize = 21.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
private fun JewelryItemPrice(price: String) {
    Text(
        text = "$$price",
        color = colorResource(id = R.color.primary_gold_color),
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 7.dp)
    )
}

@Composable
private fun JewelryItemMaterial(material: String, isCertified: Boolean) {
    Row(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
    ) {
        Text(
            text = material,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
        )
        Spacer(modifier = Modifier.width(10.dp))

        if (isCertified){
            Image(
                painter = painterResource(id = R.drawable.certified_badge),
                contentDescription = "Certified badge", // Provide appropriate content description
                modifier = Modifier.size(23.dp), // Adjust size as needed
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun JewelryItemActions(jewelryId: String, onEditJewelryClick: (String) -> Unit) {
    Row {
        Button(
            onClick = { onEditJewelryClick(jewelryId) },
            modifier = Modifier
                .height(33.dp)  // Set the height of the button
                .width(80.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.edit_button_color)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Edit", color = Color.White)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            onClick = { /* TODO: Handle delete */ },
            modifier = Modifier
                .height(33.dp)  // Set the height of the button
                .width(90.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.delete_button_color)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Delete", color = Color.White)
        }
    }
}