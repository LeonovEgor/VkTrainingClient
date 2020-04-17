package ru.leonov.vktrainingclient.di.modules

import dagger.Module
import dagger.Provides
import ru.leonov.vktrainingclient.ui.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}
