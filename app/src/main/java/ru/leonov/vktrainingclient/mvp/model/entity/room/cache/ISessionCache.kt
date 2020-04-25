package ru.leonov.vktrainingclient.mvp.model.entity.room.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession

interface ISessionCache {
    fun insertOrReplace(token: String, userId: Int): Completable
    fun getSession(): Single<UserSession>
}