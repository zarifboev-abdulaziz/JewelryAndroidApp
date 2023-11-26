package com.example.jewelryapp.detailedView


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jewelryapp.R
import com.example.jewelryapp.data.JewelryRepository


@Composable
fun DetailedView(
    jewelryId: String,
    viewModel: DetailedViewModel = DetailedViewModel(jewelryId, JewelryRepository())
) {

    val jewelry by viewModel.jewelryLiveData.observeAsState()

    if (jewelry != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .background(colorResource(R.color.bleak_yellow_light))
                .padding(16.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Title(jewelry!!.title)

            AsyncImage(
                modifier = Modifier
                    .size(width = 370.dp, height = 230.dp)
                    .padding(bottom = 10.dp),
                model = jewelry!!.imageUrl,
                contentDescription = "Jewelry image",
                placeholder = painterResource(id = R.drawable.default_jewelry_img),
                error = painterResource(id = R.drawable.default_jewelry_img),
                contentScale = ContentScale.Crop
            )

            Price(jewelry!!.price.toString())
            Material(jewelry!!.material!!)
            Certified(jewelry!!.isCertified!!)
            Size(jewelry!!.size.toString())
            Description(description = jewelry!!.description!!)
        }
    }
}

@Composable
private fun Title(title: String) {
    Text(
        text = title,
        color = Color.DarkGray,
        fontSize = 26.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Left,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
    )
}


@Composable
private fun Price(price: String) {
    Text(
        text = "$$price",
        color = colorResource(id = R.color.primary_gold_color),
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
    )
}

@Composable
private fun Material(material: String) {
    Text(
        text = "Material: $material",
        fontSize = 18.sp,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
    )
}

@Composable
private fun Certified(certified: Boolean) {
    Row(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
    ) {
        Text(
            text = "Certificate: " + (if (!certified) "-" else ""),
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
        )
        Spacer(modifier = Modifier.width(5.dp))
        if (certified){
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
private fun Size(size: String) {
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text = "Size: $size",
        fontSize = 18.sp,
    )
}

@Composable
private fun Description(description: String) {
    Text(
        modifier = Modifier.padding(bottom = 10.dp),
        text = "Description: $description",
        color = Color.Black,
        fontSize = 18.sp,
    )
}

@Composable
private fun MyDivider() {
    Divider(
        modifier = Modifier.padding(0.dp, 10.dp),
        color = Color.LightGray
    )
}