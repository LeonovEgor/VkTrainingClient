package ru.leonov.vktrainingclient.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.view.MainView
import ru.leonov.vktrainingclient.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    lateinit var token: String
    var userId: Int = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.replaceScreen(Screens.UserScreen(token, userId))
    }

    fun backClicked() {
        router.exit()
    }

    fun onTokenChanged(token: String, userId: Int) {
        this.token = token
        this.userId = userId
    }

    fun onHomeClick() {
        router.navigateTo(Screens.UserScreen(token, userId))
    }

    fun onFriendsClick() {
        router.navigateTo(Screens.FriendsScreen(token, userId))
    }

    fun onPhotosClick() {
        router.navigateTo(Screens.PhotosScreen(token, userId))
    }

    fun onLogout() {
        viewState.goToLogout()
        //router.navigateTo(Screens.Login(null, null))
    }

}
