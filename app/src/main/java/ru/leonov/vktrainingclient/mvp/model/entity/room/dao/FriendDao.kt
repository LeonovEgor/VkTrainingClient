package ru.leonov.vktrainingclient.mvp.model.entity.room.dao

import androidx.room.*
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomFriend

@Dao
interface FriendDao {

    //region Select

    @Query("SELECT * FROM RoomFriend")
    fun getAll(): List<RoomFriend>

    @Query("SELECT * FROM RoomFriend WHERE authUserId = :userId")
    fun getFriendListById(userId: Int): List<RoomFriend>?

    //endregion Select

    //region Insert

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(friend: RoomFriend)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg friends: RoomFriend)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(friends: List<RoomFriend>)

    //endregion Insert

    //region Update

    @Update
    fun update(friend: RoomFriend)

    @Update
    fun update(vararg friend: RoomFriend)

    @Update
    fun update(friends: List<RoomFriend>)

    //endregion Update

    //region Delete

    @Delete
    fun delete(friend: RoomFriend)

    @Delete
    fun delete(vararg friend: RoomFriend)

    @Delete
    fun delete(friends: List<RoomFriend>)

    //endregion Delete
}