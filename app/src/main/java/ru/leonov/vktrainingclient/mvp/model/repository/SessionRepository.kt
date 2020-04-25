package ru.leonov.vktrainingclient.mvp.model.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.leonov.vktrainingclient.mvp.model.auth.IAuth
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.ISessionCache
import ru.leonov.vktrainingclient.ui.network.NetworkStatus

class SessionRepository (
    private val auth: IAuth,
    private val networkStatus: NetworkStatus,
    private val cache: ISessionCache) {

    fun getSession(url: String): Single<UserSession> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                auth.login(url).flatMap { session->
                    cache.insertOrReplace(session.token, session.userId).toSingleDefault(session)
                }
            } else {
                cache.getSession()
            }
        }
}