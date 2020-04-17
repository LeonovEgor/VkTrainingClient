package ru.leonov.vktrainingclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsView : MvpView {
    fun init()
    fun clearError()
    fun showError(error: String)
}