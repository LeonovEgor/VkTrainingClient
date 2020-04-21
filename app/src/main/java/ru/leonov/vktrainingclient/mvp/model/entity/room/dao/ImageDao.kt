package ru.leonov.vktrainingclient.mvp.model.entity.room.dao

import androidx.room.*
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomCachedImage

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomCachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RoomCachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomCachedImage>)

    @Update
    fun update(repository: RoomCachedImage)

    @Update
    fun update(vararg repositories: RoomCachedImage)

    @Update
    fun update(repositories: List<RoomCachedImage>)

    @Delete
    fun delete(repository: RoomCachedImage)

    @Delete
    fun delete(vararg repositories: RoomCachedImage)

    @Delete
    fun delete(repositories: List<RoomCachedImage>)

    @Query("DELETE FROM RoomCachedImage")
    fun clear()

    @Query("SELECT * FROM RoomCachedImage")
    fun getAll(): List<RoomCachedImage>

    @Query("SELECT * FROM RoomCachedImage WHERE url = :url LIMIT 1")
    fun findByUrl(url: String): RoomCachedImage?
}