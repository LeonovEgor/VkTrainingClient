package ru.leonov.vktrainingclient.di

import dagger.Component
import ru.leonov.vktrainingclient.di.modules.*
import ru.leonov.vktrainingclient.mvp.presenter.*
import ru.leonov.vktrainingclient.ui.activity.MainActivity
import ru.leonov.vktrainingclient.ui.fragments.UserFragment
import ru.leonov.vktrainingclient.ui.activity.AuthActivity
import ru.leonov.vktrainingclient.ui.fragments.FriendsFragment
import ru.leonov.vktrainingclient.ui.fragments.PhotosFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepoModule::class,
        CiceroneModule::class,
        ImageModule::class,
        AuthModule::class,
        CacheModule::class,
        NetworkStatusModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(authActivity: AuthActivity)
    fun inject(authPresenter: AuthPresenter)

    fun inject(userFragment: UserFragment)
    fun inject(userPresenter: UserPresenter)

    fun inject(friendsFragment: FriendsFragment)
    fun inject(friendsPresenter: FriendsPresenter)

    fun inject(photosFragment: PhotosFragment)
    fun inject(photosPresenter: PhotosPresenter)
}
