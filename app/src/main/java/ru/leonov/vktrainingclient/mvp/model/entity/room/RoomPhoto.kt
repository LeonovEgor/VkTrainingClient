package ru.leonov.vktrainingclient.mvp.model.entity.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomUser::class,
        parentColumns = ["userId"],
        childColumns = ["authUserId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomPhoto(
    @ColumnInfo(index = true) val authUserId: Int,
    val photoUrl: String,
    val text : String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}