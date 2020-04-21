package ru.leonov.vktrainingclient.mvp.model.entity.room.dao

import androidx.room.*
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomUser

@Dao
interface UserDao {

    //region Select

    @Query("SELECT * FROM RoomUser")
    fun getAll(): List<RoomUser>

    @Query("SELECT * FROM RoomUser WHERE userId = :userId LIMIT 1")
    fun getUserById(userId: Int): RoomUser?

    //endregion Select

    //region Insert

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomUser>)

    //endregion Insert

    //region Update

    @Update
    fun update(user: RoomUser)

    @Update
    fun update(vararg user: RoomUser)

    @Update
    fun update(users: List<RoomUser>)

    //endregion Update

    //region Delete

    @Delete
    fun delete(user: RoomUser)

    @Delete
    fun delete(vararg user: RoomUser)

    @Delete
    fun delete(users: List<RoomUser>)

    //endregion Delete
}