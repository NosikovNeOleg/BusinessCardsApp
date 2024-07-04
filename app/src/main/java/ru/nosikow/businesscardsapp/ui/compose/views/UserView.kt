package ru.nosikow.businesscardsapp.ui.compose.views

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.google.gson.Gson
import ru.nosikow.businesscardsapp.model.CardModel
import ru.nosikow.businesscardsapp.model.User
import ru.nosikow.businesscardsapp.service.IUserService
import ru.nosikow.businesscardsapp.service.getUserService
import ru.nosikow.businesscardsapp.ui.compose.GenerateQrActivity


@Composable
fun UserView() {

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    val userService: IUserService = getUserService(null);
    val user: User = userService.getUser(0);

    var name by remember { mutableStateOf(user.name) }
    var bio by remember { mutableStateOf(user.bio) }
    var url by remember { mutableStateOf(user.url) }

    var isEdit by remember { mutableStateOf(false) }
    Column {
        Card( modifier = Modifier.fillMaxWidth() ) {
            Row {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Имя") },
                    readOnly = isEdit
                )
            }
            Row {
                TextField(
                    value = bio,
                    onValueChange = { bio = it },
                    label = { Text("Информация") },
                    readOnly = isEdit
                )
            }
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                TextField(
                    value = url,
                    onValueChange = { url = it },
                    label = { Text("Ссылка") },
                    readOnly = isEdit
                )
            }
        }

        Button(
            onClick = {
                isEdit = !isEdit
                user.name = name
                user.bio = bio
                user.url = url
            },
            modifier = Modifier.fillMaxWidth()
            ) {
            Text("Редактировать")
        }

        Button(
            onClick = {
                val intent = Intent(context, GenerateQrActivity::class.java)
                intent.putExtra("data", Gson().toJson(CardModel(user, Color.Green)))
                launcher.launch(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сгенерировать QR-код")
        }



    }
}


