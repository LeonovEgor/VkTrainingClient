package ru.leonov.vktrainingclient.di

import dagger.Component
import ru.leonov.vktrainingclient.di.modules.AppModule
import ru.leonov.vktrainingclient.di.modules.CiceroneModule
import ru.leonov.vktrainingclient.di.modules.ImageModule
import ru.leonov.vktrainingclient.di.modules.RepoModule
import ru.leonov.vktrainingclient.mvp.presenter.AuthPresenter
import ru.leonov.vktrainingclient.mvp.presenter.UserPresenter
import ru.leonov.vktrainingclient.mvp.presenter.MainPresenter
import ru.leonov.vktrainingclient.ui.activity.MainActivity
import ru.leonov.vktrainingclient.ui.fragments.UserFragment
import ru.leonov.vktrainingclient.ui.splash.AuthActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepoModule::class,
        CiceroneModule::class,
        ImageModule::class
//        DataBaseModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(authActivity: AuthActivity)
    fun inject(authPresenter: AuthPresenter)
    fun inject(userFragment: UserFragment)
    fun inject(userPresenter: UserPresenter)
}
