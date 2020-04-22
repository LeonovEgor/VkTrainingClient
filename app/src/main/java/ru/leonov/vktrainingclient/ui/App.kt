package ru.leonov.vktrainingclient.ui

import android.app.Application
import ru.leonov.vktrainingclient.di.AppComponent
import ru.leonov.vktrainingclient.di.DaggerAppComponent
import ru.leonov.vktrainingclient.di.modules.AppModule
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}