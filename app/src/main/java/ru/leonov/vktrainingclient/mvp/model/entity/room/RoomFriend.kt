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
data class RoomFriend(
    @PrimaryKey val friendId: Int,
    val authUserId: Int,
    val name: String,
    val photoUrl: String,
    val cityCountry: String
)