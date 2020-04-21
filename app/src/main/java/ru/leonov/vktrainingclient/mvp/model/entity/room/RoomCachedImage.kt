package ru.leonov.vktrainingclient.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCachedImage(
    @PrimaryKey val url: String,
    val localPath: String
)