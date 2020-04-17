package ru.leonov.vktrainingclient.mvp.model.repository

import ru.leonov.vktrainingclient.mvp.model.entity.api.User

data class UserResult (
    val user: User?,
    val errorCode: Int,
    val errorMsg: String
)