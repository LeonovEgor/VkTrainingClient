package ru.leonov.vktrainingclient.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.poplib.navigation.Screens
import ru.leonov.vktrainingclient.mvp.view.MainView
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
        router.replaceScreen(Screens.FriendsScreen(token, userId))
    }

    fun backClicked() {
        router.exit()
    }

    fun onTokenChanged(token: String, userId: Int) {
        this.token = token
        this.userId = userId
    }

}
