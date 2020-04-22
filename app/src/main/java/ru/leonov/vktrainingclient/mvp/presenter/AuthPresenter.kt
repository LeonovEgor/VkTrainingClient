package ru.leonov.vktrainingclient.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.repository.IAuthRepo
import ru.leonov.vktrainingclient.mvp.view.AuthView
import ru.leonov.vktrainingclient.secretdata.VkProgramId
import javax.inject.Inject

@InjectViewState
class AuthPresenter : MvpPresenter<AuthView>() {

    private val id = VkProgramId
    private val scope = "friends,photos,wall"
    private val redirectUri = "https://oauth.vk.com/blank.html"
    private val url = "https://oauth.vk.com/authorize?" +
            "client_id=${id}&" +
            "display=page&" +
            "redirect_uri=${redirectUri}&" +
            "scope=${scope}&" +
            "response_type=token&" +
            "v=5.103"

    @Inject
    lateinit var userSession: UserSession

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun login() {
        viewState.login(url)
    }

    fun logout() {
        viewState.logout()
        login()
    }

    fun onUserAuthorized(token: String, userId: Int) {
        userSession.token = token
        userSession.userId = userId
        viewState.goToNextActivity(token, userId)
    }

    fun onUserAuthorizedError(error: String) {
        userSession.token = ""
        userSession.userId = 0
        viewState.showError(error)
    }
}