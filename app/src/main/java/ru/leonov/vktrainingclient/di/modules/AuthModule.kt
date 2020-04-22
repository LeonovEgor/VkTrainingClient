package ru.leonov.vktrainingclient.di.modules

import dagger.Module
import dagger.Provides
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.repository.IAuthRepo
import ru.leonov.vktrainingclient.ui.auth.AndroidAuthRepo
import javax.inject.Named
import javax.inject.Singleton

@Module
class AuthModule {

    @Provides
    //@Singleton
    fun userSession(): UserSession {
        return UserSession()
    }

    @Named("redirectUri")
    @Provides
    fun redirectUri(): String {
        return "https://oauth.vk.com/blank.html"
    }

    @Provides
    fun authRepo(@Named("redirectUri") redirectUri: String): IAuthRepo {
        return AndroidAuthRepo(redirectUri)
    }
}