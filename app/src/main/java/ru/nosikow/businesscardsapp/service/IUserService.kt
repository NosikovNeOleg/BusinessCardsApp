package ru.nosikow.businesscardsapp.service

import ru.nosikow.businesscardsapp.model.User

interface IUserService {
    fun getUser(id: Int): User;
    fun addUser(user: User);

}