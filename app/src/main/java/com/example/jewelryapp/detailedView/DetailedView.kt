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
                .background(colorResource(R.color.bleak_yellow_light))
                .padding(16.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Title(jewelry!!.title)

            MyDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    if (jewelry!!.price != null) {
                        Price(jewelry!!.price!!)
                    }

                    if (jewelry!!.size != null) {
                        Size(jewelry!!.size!!)
                    }
                }
            }

            MyDivider()

            if (jewelry!!.description != null) {
                Description(description = jewelry!!.description!!)
            }

            Spacer(Modifier.height(10.dp))

            Image(
                painterResource(R.drawable.cinema),
                stringResource(id = R.string.cinema_icon_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp)
            )
        }
    }
}

@Composable
private fun Title(title: String) {
    Text(
        text = title,
        color = Color.Black,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Left
    )
}

//Todo to be deleted
//@Composable
//private fun Rating(rating: Double) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Image(
//            painterResource(R.drawable.baseline_star_half_24),
//            stringResource(id = R.string.cinema_icon_desc),
//            contentScale = ContentScale.Crop
//        )
//
//        Text(
//            text = rating.toString(),
//            color = Color.Black,
//            fontWeight = FontWeight.Bold,
//            fontSize = 23.sp,
//            fontFamily = FontFamily.SansSerif,
//            textAlign = TextAlign.Center
//        )
//    }
//}

@Composable
private fun Price(price: Double) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.detailed_view_price_label, price),
        color = Color.Black,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}


@Composable
private fun Size(size: Int) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = size.toString(),
        color = Color.Black,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
private fun Description(description: String) {
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = description,
        color = Color.DarkGray,
        fontSize = 20.sp,
        fontFamily = FontFamily.SansSerif
    )
}

//Todo to be deleted
//@Composable
//private fun Actors(actors: List<String>) {
//    Column(modifier = Modifier.fillMaxWidth()) {
//        var i = 0
//        for (actor in actors) {
//            ActorTextView(actor = actor, ++i == actors.size)
//        }
//    }
//}
//
//@Composable
//private fun ActorTextView(actor: String, isTheLastOne: Boolean) {
//    Text(
//        modifier = Modifier.padding(3.dp, 1.dp),
//        text = if (isTheLastOne) actor else "$actor,",
//        color = Color.DarkGray,
//        fontSize = 19.sp,
//        fontFamily = FontFamily.SansSerif,
//        fontStyle = FontStyle.Italic
//    )
//}


@Composable
private fun MyDivider() {
    Divider(
        modifier = Modifier.padding(0.dp, 10.dp),
        color = Color.LightGray

    )
}