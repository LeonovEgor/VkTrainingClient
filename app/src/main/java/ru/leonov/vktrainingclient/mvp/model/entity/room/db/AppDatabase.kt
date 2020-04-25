package ru.leonov.vktrainingclient.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.leonov.vktrainingclient.mvp.model.entity.room.*
import ru.leonov.vktrainingclient.mvp.model.entity.room.dao.*

@Database(
    entities = [
        RoomUser::class,
        RoomFriend::class,
        RoomCachedImage::class,
        RoomPhoto::class,
        RoomSession::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val friendDao: FriendDao
    abstract val imageDao: ImageDao
    abstract val photoDao: PhotoDao
    abstract val sessionDao: SessionDao

    companion object {
        const val DB_NAME = "database.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context) = instance
            ?: let {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, DB_NAME
                )
                    //.addMigrations(MIGRATION_1_2)
                    .build()
            }
    }
}