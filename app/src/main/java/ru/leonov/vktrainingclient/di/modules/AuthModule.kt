package ru.leonov.vktrainingclient.di.modules

import dagger.Module
import dagger.Provides
import ru.leonov.vktrainingclient.mvp.model.auth.IAuth
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.cache.ISessionCache
import ru.leonov.vktrainingclient.mvp.model.repository.SessionRepository
import ru.leonov.vktrainingclient.ui.auth.AndroidAuth
import ru.leonov.vktrainingclient.ui.network.NetworkStatus
import javax.inject.Named
import javax.inject.Singleton

@Module    (
    includes = [
        CacheModule::class,
        NetworkStatusModule::class
    ]
)
class AuthModule {

    @Provides
    @Singleton
    fun userSession(): UserSession {
        return UserSession()
    }

    @Named("redirectUri")
    @Provides
    fun redirectUri(): String {
        return "https://oauth.vk.com/blank.html"
    }

    @Provides
    @Singleton
    fun androidAuth(@Named("redirectUri") redirectUri: String): IAuth =
        AndroidAuth(redirectUri)

    @Provides
    @Singleton
    fun authRepository(
        auth: IAuth,
        networkStatus: NetworkStatus,
        cache: ISessionCache
    ) = SessionRepository(auth, networkStatus, cache)

}