package ru.nosikow.businesscardsapp.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
class GenerateQrActivity  : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenerateQr({finish()}, intent.extras?.get("data").toString())
        }
    }
}



@Composable
private fun GenerateQr(property: () -> Unit, data: String) {
    AsyncImage(
        model = "https://api.qrserver.com/v1/create-qr-code/?data=$data",
        contentDescription = "qrCode",
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clickable {
                property()
            }
    )
}

