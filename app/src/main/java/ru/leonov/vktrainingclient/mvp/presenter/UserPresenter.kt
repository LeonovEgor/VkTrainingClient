package ru.leonov.vktrainingclient.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.repository.UsersRepository
import ru.leonov.vktrainingclient.mvp.view.UserView
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class UserPresenter( private val mainThreadScheduler: Scheduler, private val userId: Int) : MvpPresenter<UserView>() {

    private val fields = "photo_200, country, city, bdate"

    @Inject
    lateinit var userRepo: UsersRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userSession: UserSession


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadUser()
    }

    private fun loadUser() {
        viewState.clearError()
        userRepo.getUser(userId, fields, userSession.token)
            .observeOn(mainThreadScheduler)
            .subscribe({ user ->
                    viewState.setUserName(user.name)
                    viewState.loadPhoto(user.photoUrl)
                    viewState.setCity(user.cityCountry)
                    viewState.setBirthday("День рождения: ${user.bdate}")
            }, {
                viewState.showError(it.localizedMessage ?: "unknown error")
            })
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}