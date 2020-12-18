package ru.leonov.vktrainingclient.navigation

import ru.leonov.vktrainingclient.ui.fragments.FriendsFragment
import ru.leonov.vktrainingclient.ui.fragments.UserFragment
import ru.leonov.vktrainingclient.ui.fragments.PhotosFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class PhotosScreen : SupportAppScreen() {
        override fun getFragment() = PhotosFragment()
    }

    class UserScreen(private val userId: Int) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(userId)
    }

    class FriendsScreen : SupportAppScreen() {
        override fun getFragment() = FriendsFragment()
    }
}