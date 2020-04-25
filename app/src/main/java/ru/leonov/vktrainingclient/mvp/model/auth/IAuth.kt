package ru.leonov.vktrainingclient.mvp.model.auth

import io.reactivex.rxjava3.core.Single
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession

interface IAuth {
    fun login(url: String): Single<UserSession>
    fun logout()
}