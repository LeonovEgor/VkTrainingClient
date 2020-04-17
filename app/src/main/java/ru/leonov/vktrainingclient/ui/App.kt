package ru.leonov.vktrainingclient.ui

import android.app.Application
//import com.vk.sdk.VKAccessToken
//import com.vk.sdk.VKAccessTokenTracker
//import com.vk.sdk.VKSdk
import ru.leonov.vktrainingclient.di.AppComponent
import ru.leonov.vktrainingclient.di.DaggerAppComponent
import ru.leonov.vktrainingclient.di.modules.AppModule
import timber.log.Timber
import java.util.regex.Pattern

class App : Application() {

    companion object {
        lateinit var instance: App

    }

//    var token: VKAccessToken? = null
//    private set

//    private val vkAccessTokenTracker = object : VKAccessTokenTracker() {
//
//        override fun onVKAccessTokenChanged(oldToken: VKAccessToken?, newToken: VKAccessToken?) {
//            token = newToken
//        }
//    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

//        vkAccessTokenTracker.startTracking()
//        VKSdk.initialize(this)
    }

    override fun onTerminate() {
//        vkAccessTokenTracker.stopTracking()
        super.onTerminate()
    }
}