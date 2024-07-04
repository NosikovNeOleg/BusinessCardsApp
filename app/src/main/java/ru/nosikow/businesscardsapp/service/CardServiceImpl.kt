package ru.nosikow.businesscardsapp.service

import android.media.Image
import androidx.compose.ui.graphics.Color
import retrofit2.Call

import ru.nosikow.businesscardsapp.model.CardModel
import ru.nosikow.businesscardsapp.model.User

class CardServiceStub : ICardService {

    private val cards: ArrayList<CardModel> = arrayListOf(
        CardModel(User("name1","bio1","url1"), Color.Red),
        CardModel(User("name2","bio2","url2"), Color.Red),
        CardModel(User("name3","bio3","url3"), Color.Red),
    )
    override fun getCards(): ArrayList<CardModel> {
        return cards;
    }

    override fun saveCard(card: CardModel) {
        cards.add(card)
    }


}

class CardServiceImpl(
    var url: String
) : ICardService {
    override fun getCards(): List<CardModel> {
        TODO("Not yet implemented")
    }

    override fun saveCard(card: CardModel) {
        TODO("Not yet implemented")
    }
}

fun getCardService(url : String?) : ICardService {
    return if (url != null) {
        CardServiceImpl(url)
    } else {
        CardServiceStub()
    }
}