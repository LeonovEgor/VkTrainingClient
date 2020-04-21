package ru.leonov.vktrainingclient.mvp.model.entity.room.dao

import androidx.room.*
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomPhoto


@Dao
interface PhotoDao {

    //region Select

    @Query("SELECT * FROM RoomPhoto")
    fun getAll(): List<RoomPhoto>

    @Query("SELECT * FROM RoomPhoto WHERE authUserId = :userId")
    fun getPhotoListById(userId: Int): List<RoomPhoto>?

    //endregion Select

    //region Insert

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: RoomPhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photos: RoomPhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<RoomPhoto>)

    //endregion Insert

    //region Update

    @Update
    fun update(photo: RoomPhoto)

    @Update
    fun update(vararg photo: RoomPhoto)

    @Update
    fun update(photos: List<RoomPhoto>)

    //endregion Update

    //region Delete

    @Delete
    fun delete(photo: RoomPhoto)

    @Delete
    fun delete(vararg photo: RoomPhoto)

    @Delete
    fun delete(photos: List<RoomPhoto>)

    //endregion Delete
}