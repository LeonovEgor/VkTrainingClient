package ru.leonov.vktrainingclient.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.view.MainView
import ru.leonov.vktrainingclient.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userSession: UserSession

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.replaceScreen(Screens.UserScreen(userSession.userId))
    }

    fun backClicked() {
        router.exit()
    }

    fun onHomeClick() {
        router.navigateTo(Screens.UserScreen(userSession.userId))
    }

    fun onFriendsClick() {
        router.navigateTo(Screens.FriendsScreen())
    }

    fun onPhotosClick() {
        router.navigateTo(Screens.PhotosScreen())
    }

    fun onLogout() {
        viewState.goToLogout()
    }

}