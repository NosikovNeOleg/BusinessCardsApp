package ru.nosikow.businesscardsapp.ui.compose.tabs

import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CardsTab(state: Int,index: Int,setState: (Int) -> Unit,) {
    Tab(
        text = { Text("Карточки") },
        selected = state == index,
        onClick = { setState(index) }
    )
}