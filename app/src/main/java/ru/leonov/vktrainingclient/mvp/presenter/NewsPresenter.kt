package ru.leonov.vktrainingclient.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.view.NewsView


@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }
}
