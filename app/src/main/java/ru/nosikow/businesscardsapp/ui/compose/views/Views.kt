package ru.nosikow.businesscardsapp.ui.compose.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun Views(index : Int) {
    when (index) {
        0 -> UserView();
        1 -> CardsView();
        else ->  Text("" + index);
    }
}