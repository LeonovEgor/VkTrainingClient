package ru.leonov.vktrainingclient.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.leonov.vktrainingclient.mvp.model.repository.PhotosRepository
import ru.leonov.vktrainingclient.mvp.model.repository.UsersRepository
import javax.inject.Singleton

@Module
    (
    includes = [
//        CacheModule::class,
        ApiModule::class
    ]
)
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource
    ): UsersRepository {
        return UsersRepository(api)
    }

    @Singleton
    @Provides
    fun photosRepo(
        api: IDataSource
    ): PhotosRepository {
        return PhotosRepository(api)
    }


}