package ru.leonov.vktrainingclient.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUser(
    @PrimaryKey val userId: Int,
    val name: String,
    val bdate: String,
    val photoUrl: String,
    val cityCountry: String
)