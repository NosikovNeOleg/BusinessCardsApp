package ru.nosikow.businesscardsapp.service

import ru.nosikow.businesscardsapp.model.User

class UserServiceStub : IUserService {



    override fun getUser(id: Int): User {
        return User("Иван","Разработчик","nosikow.ru")
    }

    override fun addUser(user: User) {
        TODO("Not yet implemented")
    }
}

class UserServiceImpl(
    var url: String
) : IUserService {
    override fun getUser(id: Int): User {
        TODO("Not yet implemented")
    }

    override fun addUser(user: User) {
        TODO("Not yet implemented")
    }
}

fun getUserService(url : String?) : IUserService {
    return if (url != null) {
        UserServiceImpl(url)
    } else {
        UserServiceStub()
    }
}