package ru.nosikow.businesscardsapp.ui.compose.views

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import ru.nosikow.businesscardsapp.model.CardModel
import ru.nosikow.businesscardsapp.service.ICardService
import ru.nosikow.businesscardsapp.service.getCardService
import ru.nosikow.businesscardsapp.ui.compose.GenerateQrActivity
import ru.nosikow.businesscardsapp.ui.compose.QRCodeScanner

@Composable
fun CardsView() {
    var scannedCode by remember { mutableStateOf("") }
    var showScanner by remember { mutableStateOf(false) }
    val cardService: ICardService = getCardService(null);
    val cards = remember { mutableStateOf(listOf<CardModel>() + cardService.getCards()) }


    Column (
        modifier = Modifier.verticalScroll(rememberScrollState())
    )
    {
        if (showScanner) {
            QRCodeScanner(
                onQRCodeScanned = { qrCode ->
                    showScanner = false
                    if (scannedCode != qrCode) {
                        scannedCode = qrCode
                        val card = Gson().fromJson(scannedCode, CardModel::class.java)
                        cards.value += card
                    }
                },
                onDismiss = {
                    showScanner = false
                }
            )
        } else {
            Button(onClick = { showScanner = true }) {
                Text(text = "Сканировать Qr-код")
            }

            cards.value.forEach {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Red)
                ) {
                    Row {
                        TextField(
                            value = it.user.name,
                            onValueChange = { },
                            label = { Text("Имя") },
                            readOnly = true
                        )
                    }
                    Row {
                        TextField(
                            value = it.user.bio,
                            onValueChange = {},
                            label = { Text("Информация") },
                            readOnly = true
                        )
                    }
                    Row {
                        TextField(
                            value = it.user.url,
                            onValueChange = {},
                            label = { Text("Ссылка") },
                            readOnly = true
                        )
                    }
                }
            }
        }
    }
}


