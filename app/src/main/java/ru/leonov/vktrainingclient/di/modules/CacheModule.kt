package ru.leonov.vktrainingclient.di.modules

import dagger.Module
import dagger.Provides
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.IFriendsCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.IPhotosCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.ISessionCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.IUserCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase
import ru.leonov.vktrainingclient.mvp.model.repository.cache.UserCache
import ru.leonov.vktrainingclient.mvp.model.repository.cache.FriendsCache
import ru.leonov.vktrainingclient.mvp.model.repository.cache.PhotosCache
import ru.leonov.vktrainingclient.mvp.model.repository.cache.SessionCache

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