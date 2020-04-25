package ru.leonov.vktrainingclient.di.modules

import dagger.Module
import dagger.Provides
import ru.leonov.vktrainingclient.mvp.model.api.IDataSource
import ru.leonov.vktrainingclient.mvp.model.cache.IFriendsCache
import ru.leonov.vktrainingclient.mvp.model.cache.IPhotosCache
import ru.leonov.vktrainingclient.mvp.model.cache.IUserCache
import ru.leonov.vktrainingclient.mvp.model.repository.FriendsRepository
import ru.leonov.vktrainingclient.mvp.model.repository.PhotosRepository
import ru.leonov.vktrainingclient.mvp.model.repository.UsersRepository
import ru.leonov.vktrainingclient.ui.network.NetworkStatus
import javax.inject.Singleton

@Module
    (
    includes = [
        CacheModule::class,
        ApiModule::class,
        NetworkStatusModule::class
    ]
)
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: NetworkStatus,
        cache: IUserCache
    ): UsersRepository {
        return UsersRepository(api, networkStatus, cache)
    }

    @Singleton
    @Provides
    fun friendsRepo(
        api: IDataSource,
        networkStatus: NetworkStatus,
        cache: IFriendsCache
    ): FriendsRepository {
        return FriendsRepository(api, networkStatus, cache)
    }

    @Singleton
    @Provides
    fun photosRepo(
        api: IDataSource,
        networkStatus: NetworkStatus,
        cache: IPhotosCache
    ): PhotosRepository {
        return PhotosRepository(api, networkStatus, cache)
    }
}