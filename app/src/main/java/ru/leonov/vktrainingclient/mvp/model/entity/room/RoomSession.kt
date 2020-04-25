package ru.leonov.vktrainingclient.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomSession(
    @PrimaryKey val userId: Int,
    val token: String
)