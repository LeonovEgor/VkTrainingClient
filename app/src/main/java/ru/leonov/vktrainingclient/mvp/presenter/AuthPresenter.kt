package ru.leonov.vktrainingclient.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.utils.getParameter
import ru.leonov.vktrainingclient.mvp.view.AuthView
import ru.leonov.vktrainingclient.secretdata.VkProgramId

@InjectViewState
class AuthPresenter: MvpPresenter<AuthView>() {

    private val accessTokenParamName = "access_token"
    private val userIdParamName = "id"
    private val errorParamName = "error_msg"

    private val id = VkProgramId
    private val scope = "friends,photos,wall"
    private val redirect_uri = "https://oauth.vk.com/blank.html"

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        login()
    }

    private fun login() {
        val url = "https://oauth.vk.com/authorize?" +
                "client_id=${id}&" +
                "display=page&" +
                "redirect_uri=${redirect_uri}&" +
                "scope=${scope}&" +
                "response_type=token&" +
                "v=5.103"
        viewState.login(url)
    }

    private fun onUserAuthorized(token: String, userId: Int ) {
        viewState.goToNextActivity(token, userId)
    }

    private fun onUserAuthorizedError(error: String) {
        viewState.showError(error)
    }

    fun onAuthResponse(url: String) {
        if (url.contains(redirect_uri)) {
            val token = url.getParameter(accessTokenParamName)
            val userId = url.getParameter(userIdParamName)

            token?.let {
                onUserAuthorized(it, userId?.toInt() ?: 0)
                return
            }

            val error = url.getParameter(errorParamName)
            error?.let {
                onUserAuthorizedError(it)
            }
        }
    }

}