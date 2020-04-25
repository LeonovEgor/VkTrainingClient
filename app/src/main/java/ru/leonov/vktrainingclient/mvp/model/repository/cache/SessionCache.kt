package ru.leonov.vktrainingclient.mvp.model.repository.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomSession
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.ISessionCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase

class SessionCache(private val database: AppDatabase) : ISessionCache {

    override fun insertOrReplace(token: String, userId: Int): Completable =
        Completable.fromAction() {
            database.sessionDao.insert(RoomSession(userId, token))
    }.subscribeOn(Schedulers.io())

    override fun getSession(): Single<UserSession> =
        Single.create<UserSession> { emitter ->
            database.sessionDao.getSession()?.let { roomSession ->
                val session = UserSession(roomSession.token, roomSession.userId)
                emitter.onSuccess(session)
            } ?: let {
                emitter.onError(RuntimeException("No such session in cache"))
            }
        }.subscribeOn(Schedulers.io())
}