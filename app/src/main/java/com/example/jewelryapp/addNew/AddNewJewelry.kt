package com.example.jewelryapp.addNew


import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jewelryapp.MainActivity
import com.example.jewelryapp.R
import com.example.jewelryapp.data.JewelryRepository
import com.example.jewelryapp.data.dataClasses.Jewelry
import java.text.ParseException
import java.text.SimpleDateFormat


@Composable
fun AddNewJewelry(
    viewModel: AddNewJewelryViewModel = AddNewJewelryViewModel(JewelryRepository())
) {
    val localContext = LocalContext.current

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val size = remember { mutableStateOf("") }
    val material = remember { mutableStateOf("") }
    val isCertified = remember { mutableStateOf(false) }
    val imageUrl = remember { mutableStateOf("") }

    val response by viewModel.insertResponseLiveData.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CreateNewJewelryPageTitle()

            Spacer(Modifier.height(15.dp))
            TitleInput(title = title.value, onTitleChange = { title.value = it })

            Spacer(Modifier.height(15.dp))
            DescriptionInput(description = description.value, onDescriptionChange = { description.value = it })

            Spacer(modifier = Modifier.height(15.dp))
            Price(price = price.value, onPriceChanged = { price.value = it })

            Spacer(modifier = Modifier.height(15.dp))
            Size(size = size.value, onSizeChanged = { size.value = it })

            Spacer(modifier = Modifier.height(15.dp))
            MaterialInput(material = material.value, onMaterialChange = { material.value = it })

            Spacer(modifier = Modifier.height(15.dp))
            Certified(certified = isCertified.value, onCertifiedChange = { isCertified.value = it})

            Spacer(modifier = Modifier.height(15.dp))
            ImageUrl(imageUrl = imageUrl.value, onUrlChange = { imageUrl.value = it })

            Spacer(Modifier.height(16.dp))
            AddNewButton {
                val constructedJewelry: Jewelry? = constructJewelryIfInputValid(
                    titleInput = title.value,
                    descriptionInput = description.value,
                    priceInput = price.value,
                    sizeInput = size.value,
                    materialInput = material.value,
                    certifiedInput = isCertified.value,
                    imageUrlInput = imageUrl.value,
                    context = localContext
                )

                if (constructedJewelry != null
                ) {
                    viewModel.saveNewJewelry(
                        constructedJewelry
                    )
                }
            }
        }

        if (response != null) {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 19.sp,
                text = if (response!!.status == "OK") stringResource(id = R.string.saved_success_msg)
                else stringResource(id = R.string.saved_fail_msg)
            )

            if (response!!.status == "OK") {
                localContext.startActivity(Intent(localContext, MainActivity::class.java))
            }
        }
    }
}

@Composable
private fun CreateNewJewelryPageTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.title_activity_add_new_jewelry),
        color = Color.Black,
        fontSize = 26.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleInput(title: String, onTitleChange: (String) -> Unit) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.jewelry_title_input_label),
        color = Color.Black,
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
    )

    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.input_field_grey)
        ),
        value = title,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onTitleChange(it) },
  )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DescriptionInput(description: String, onDescriptionChange: (String) -> Unit) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.jewelry_desc_input_label),
        color = Color.Black,
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
    )

    TextField(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.input_field_grey)
        ),
        value = description,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onDescriptionChange(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Price(price: String, onPriceChanged: (String) -> Unit) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.jewelry_price_input_label),
        color = Color.Black,
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
    )

    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.input_field_grey)
        ),
        value = price,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onPriceChanged(it) },
        label = {
            Text(stringResource(id = R.string.jewelry_price_input_hint))
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Size(size: String, onSizeChanged: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 3.dp),
            text = stringResource(id = R.string.jewelry_size_input_label),
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif
        )

        TextField(modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = colorResource(id = R.color.input_field_grey)
            ),
            value = size,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { onSizeChanged(it) },
            label = {
                Text(stringResource(id = R.string.jewelry_size_input_hint))
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MaterialInput(material: String, onMaterialChange: (String) -> Unit) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.jewelry_material_input_label),
        color = Color.Black,
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.input_field_grey)
        ),
        value = material,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onMaterialChange(it) }
    )
}


@Composable
private fun Certified(certified: Boolean, onCertifiedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.padding(bottom = 3.dp),
            text = stringResource(id = R.string.jewelry_certified_input_label),
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.width(8.dp))
        Checkbox(checked = certified, onCheckedChange = onCertifiedChange)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageUrl(imageUrl: String, onUrlChange: (String) -> Unit) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.jewelry_image_input_label),
        color = Color.Black,
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.input_field_grey)
        ),
        value = imageUrl,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onUrlChange(it) },
        label = {
            Text(stringResource(id = R.string.jewelry_image_input_hint))
        }
    )
}

@Composable
private fun AddNewButton(onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.save_btn_color), contentColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            fontSize = 17.sp, text = stringResource(id = R.string.save_btn_text)
        )
    }
}

private fun constructJewelryIfInputValid(
    titleInput: String?,
    descriptionInput: String?,
    priceInput: String?,
    sizeInput: String?,
    materialInput: String?,
    certifiedInput: Boolean?,
    imageUrlInput: String?,
    context: Context
): Jewelry? {
    if (titleInput.isNullOrEmpty() ||
        descriptionInput.isNullOrEmpty() ||
        priceInput.isNullOrEmpty() ||
        sizeInput.isNullOrEmpty() ||
        materialInput.isNullOrEmpty() ||
        imageUrlInput.isNullOrEmpty()
    ) {
        Toast.makeText(
            context, context.resources.getString(R.string.jewelry_all_fields_compulsory_warning),
            Toast.LENGTH_SHORT
        ).show()
        return null
    }

    return Jewelry(
        title = titleInput,
        description = descriptionInput,
        price = priceInput.toDouble(),
        size = sizeInput.toInt(),
        material = materialInput,
        isCertified = certifiedInput,
        imageUrl = imageUrlInput
    )
}