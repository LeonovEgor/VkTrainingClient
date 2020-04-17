package ru.geekbrains.poplib.navigation

import ru.leonov.vktrainingclient.ui.fragments.UserFragment
import ru.leonov.vktrainingclient.ui.fragments.NewsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class NewsScreen : SupportAppScreen() {
        override fun getFragment() = NewsFragment.newInstance()
    }

    class FriendsScreen(private val token: String, private val userId: Int) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(token, userId)
    }
}