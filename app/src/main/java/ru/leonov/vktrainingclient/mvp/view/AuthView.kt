package ru.leonov.vktrainingclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AuthView : MvpView {
    fun init()

    fun goToNextActivity(token: String, userId: Int)
    fun showError(error: String)
    fun login(url: String)
    fun logout()
}