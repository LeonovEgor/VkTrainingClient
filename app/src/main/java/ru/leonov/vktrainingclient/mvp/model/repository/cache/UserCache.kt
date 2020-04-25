package ru.leonov.vktrainingclient.mvp.model.repository.cache

import io.reactivex.rxjava3.core.Single
import ru.leonov.vktrainingclient.mvp.model.entity.VkUser
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomUser
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.IUserCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase

class UserCache(private val database: AppDatabase) : IUserCache {

    override fun insertOrReplace(user: VkUser) {
        val roomUser = RoomUser(
            user.userId,
            user.name,
            user.bdate,
            user.photoUrl,
            user.cityCountry
        )
        database.userDao.insert(roomUser)
    }

    override fun getUserById(userId: Int): Single<VkUser> =

        Single.create { emitter ->
            database.userDao.getUserById(userId)?.let { roomVkUser ->
                emitter.onSuccess(
                    VkUser(
                        roomVkUser.userId,
                        roomVkUser.name,
                        roomVkUser.bdate,
                        roomVkUser.photoUrl,
                        roomVkUser.cityCountry
                    )
                )
            } ?: let {
                emitter.onError(RuntimeException("No such user in cache"))
            }
        }
}