package ru.leonov.vktrainingclient.mvp.model.entity.room.dao

import androidx.room.*
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomSession

@Dao
interface SessionDao {

    //region Select

    @Query("SELECT * FROM RoomSession LIMIT 1")
    fun getSession(): RoomSession?

    //endregion Select

    //region Insert

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(session: RoomSession)

    //endregion Insert

    //region Update

    @Update
    fun update(session: RoomSession)

    //endregion Update

    //region Delete

    @Delete
    fun delete(session: RoomSession)

    //endregion Delete
}