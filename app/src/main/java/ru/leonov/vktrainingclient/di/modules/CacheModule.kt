package ru.leonov.vktrainingclient.di.modules

import dagger.Module
import dagger.Provides
import ru.leonov.vktrainingclient.mvp.model.cache.IFriendsCache
import ru.leonov.vktrainingclient.mvp.model.cache.IPhotosCache
import ru.leonov.vktrainingclient.mvp.model.cache.ISessionCache
import ru.leonov.vktrainingclient.mvp.model.cache.IUserCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase
import ru.leonov.vktrainingclient.mvp.model.cache.room.UserCache
import ru.leonov.vktrainingclient.mvp.model.cache.room.FriendsCache
import ru.leonov.vktrainingclient.mvp.model.cache.room.PhotosCache
import ru.leonov.vktrainingclient.mvp.model.cache.room.SessionCache

@Module(
    includes = [
        DataBaseModule::class
    ]
)
class CacheModule {

    @Provides
    fun userCache(database: AppDatabase): IUserCache {
        return UserCache(
            database
        )
    }

    @Provides
    fun friendsCache(database: AppDatabase): IFriendsCache {
        return FriendsCache(
            database
        )
    }

    @Provides
    fun photosCache(database: AppDatabase): IPhotosCache {
        return PhotosCache(
            database
        )
    }

    @Provides
    fun sessionCache(database: AppDatabase): ISessionCache {
        return SessionCache(
            database
        )
    }
}