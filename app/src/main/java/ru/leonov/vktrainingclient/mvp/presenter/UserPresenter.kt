package ru.leonov.vktrainingclient.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.model.repository.UsersRepository
import ru.leonov.vktrainingclient.mvp.utils.concatString
import ru.leonov.vktrainingclient.mvp.view.UserView
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject


@InjectViewState
class UserPresenter(
    private val mainThreadScheduler: Scheduler,
    private val token: String,
    private val userId: Int
) : MvpPresenter<UserView>() {

    private val fields = "photo_200, country, city, bdate"

    @Inject
    lateinit var userRepo: UsersRepository

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadUser()
    }

    private fun loadUser() {
        viewState.clearError()
        userRepo.getUser(userId, fields, token)
            .observeOn(mainThreadScheduler)
            .subscribe ( { user ->
                Timber.d(user.data?.first_name)
                if (user.errorCode != 0) {
                    viewState.showError(user.errorMsg)
                } else {
                    user.data?.let {
                        viewState.setUserName("${it.first_name} ${it.last_name}")
                        viewState.loadPhoto(it.photo_200)
                        val cityCountry = concatString(it.city?.title, ", ", it.country?.title)
                        viewState.setCity(cityCountry)
                        viewState.setBirthday("День рождения: ${it.bdate}")
                    }
                }
            }, {
              viewState.showError(it.localizedMessage ?: "unknown error")
            })
    }

    fun backClicked() : Boolean {
        router.exit()
        return true
    }

}
