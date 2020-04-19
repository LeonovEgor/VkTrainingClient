package ru.geekbrains.poplib.navigation

import ru.leonov.vktrainingclient.ui.fragments.FriendsFragment
import ru.leonov.vktrainingclient.ui.fragments.UserFragment
import ru.leonov.vktrainingclient.ui.fragments.PhotosFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class PhotosScreen(private val token: String, private val userId: Int) : SupportAppScreen() {
        override fun getFragment() = PhotosFragment.newInstance(token, userId)
    }

    class UserScreen(private val token: String, private val userId: Int) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(token, userId)
    }

    class FriendsScreen(private val token: String, private val userId: Int) : SupportAppScreen() {
        override fun getFragment() = FriendsFragment.newInstance(token, userId)
    }
}