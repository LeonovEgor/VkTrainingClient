package ru.leonov.vktrainingclient.mvp.model.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.leonov.vktrainingclient.mvp.model.entity.VkUser
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomFriend
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.IFriendsCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase

class FriendsCache(private val database: AppDatabase) : IFriendsCache {

    override fun insertOrReplace(userId: Int, friendList: List<VkUser>): Completable =
        Completable.fromAction {
            val roomVkFriendList = friendList.map {
                    RoomFriend(
                        it.userId,
                        userId,
                        it.name,
                        it.photoUrl,
                        it.cityCountry
                    )
            }
            database.friendDao.insert(roomVkFriendList)
        }.subscribeOn(Schedulers.io())

    override fun getFriendListById(userId: Int): Single<List<VkUser>> =

        Single.create { emitter ->
            database.friendDao.getFriendListById(userId)?.let { roomVkFriendList ->
                val vkFriendList = roomVkFriendList.map {
                    VkUser(
                        it.authUserId,
                        it.name,
                        "",
                        it.photoUrl,
                        it.cityCountry
                    )
                }
                emitter.onSuccess(vkFriendList)
            } ?: let {
                emitter.onError(RuntimeException("No such friends in cache"))
            }
        }
}