package ru.leonov.vktrainingclient.mvp.model.entity

data class UserRequestData(
    val usersId: Int,
    val fields: String,
    val nameCase: NameCase,
    val token: String
)