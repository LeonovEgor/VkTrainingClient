package ru.leonov.vktrainingclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun init()
    fun clearError()
    fun showError(error: String)
    fun setUserName(userName: String)
    fun loadPhoto(url: String)
    fun setCity(city: String)
    fun setBirthday(date: String)
}