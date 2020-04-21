package ru.leonov.vktrainingclient.mvp.model.entity

data class VkUser(
    val userId: Int,
    val name: String,
    val bdate: String,
    val photoUrl: String,
    val cityCountry: String
)