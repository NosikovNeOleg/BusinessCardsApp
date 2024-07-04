package ru.nosikow.businesscardsapp.service


import ru.nosikow.businesscardsapp.model.CardModel

interface ICardService {
    fun getCards(): List<CardModel>;
    fun saveCard(card: CardModel);
}