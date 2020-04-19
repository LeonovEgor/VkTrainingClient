package ru.leonov.vktrainingclient.mvp.model.entity

data class UserSession(
    val token: String?,
    val userId: String?,
    val error: String?
)