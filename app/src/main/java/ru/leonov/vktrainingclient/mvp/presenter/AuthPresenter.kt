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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        //login()
    }

    fun login() {
        val url = "https://oauth.vk.com/authorize?" +
                "client_id=${id}&" +
                "display=page&" +
                "redirect_uri=${redirectUri}&" +
                "scope=${scope}&" +
                "response_type=token&" +
                "v=5.103"

        viewState.login(url)
    }

    fun logout() {
        viewState.logout()
        login()
    }

    private fun onUserAuthorized(token: String, userId: Int) {
        viewState.goToNextActivity(token, userId)
    }

    private fun onUserAuthorizedError(error: String) {
        viewState.showError(error)
    }

    fun authResponse(userSession: UserSession) {
        userSession.token?.let {
            onUserAuthorized(it, userSession.userId?.toInt() ?: 0)
            return
        }

        userSession.error?.let {
            onUserAuthorizedError(it)
        } ?: let {
            onUserAuthorizedError("Unknown error!!!")
        }
    }
}