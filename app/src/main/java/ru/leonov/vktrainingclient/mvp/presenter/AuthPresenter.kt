package ru.leonov.vktrainingclient.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.repository.SessionRepository
import ru.leonov.vktrainingclient.mvp.view.AuthView
import ru.leonov.vktrainingclient.secretdata.VkProgramId
import javax.inject.Inject

@InjectViewState
class AuthPresenter(private val mainThreadScheduler: Scheduler) : MvpPresenter<AuthView>() {

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

    @Inject
    lateinit var session: SessionRepository

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun login() {
        session.getSession(url)
            .observeOn(mainThreadScheduler)
            .subscribe({ newSession ->
                onUserAuthorized(newSession.token, newSession.userId)
            }, {
                onUserAuthorizedError(it.localizedMessage ?: "Unknown authorization error!!!")
            })
    }

    fun logout() {
        viewState.logout()
        login()
    }

    private fun onUserAuthorized(token: String, userId: Int) {
        userSession.token = token
        userSession.userId = userId
        viewState.goToNextActivity(token, userId)
    }

    private fun onUserAuthorizedError(error: String) {
        userSession.token = ""
        userSession.userId = 0
        viewState.showError(error)
    }
}