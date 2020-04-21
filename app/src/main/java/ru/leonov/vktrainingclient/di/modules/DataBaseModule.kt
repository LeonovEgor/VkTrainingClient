package ru.leonov.vktrainingclient.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase
import ru.leonov.vktrainingclient.ui.App
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun database(app: App): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DB_NAME )
//            .addMigrations(MIGRATION_1_2)
//            .addMigrations(MIGRATION_2_3)
            .build()
    }
}