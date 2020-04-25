package ru.leonov.vktrainingclient.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomUser::class,
        parentColumns = ["userId"],
        childColumns = ["authUserId"]
    )]
)
data class RoomPhoto(
    @PrimaryKey val photoUrl: String,
    val authUserId: Int,
    val text : String
)