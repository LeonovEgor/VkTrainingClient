package ru.leonov.vktrainingclient.di.modules

import dagger.Module
import dagger.Provides
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.network.NetworkStatus
import javax.inject.Singleton

@Module
class NetworkStatusModule {

    @Singleton
    @Provides
    fun networkStatus(app: App): NetworkStatus {
        return NetworkStatus(app)
    }

}