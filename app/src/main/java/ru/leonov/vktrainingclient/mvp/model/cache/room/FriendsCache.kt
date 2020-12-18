package ru.leonov.vktrainingclient.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.leonov.vktrainingclient.mvp.model.entity.VkUser
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomFriend
import ru.leonov.vktrainingclient.mvp.model.cache.IFriendsCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase
import timber.log.Timber

class FriendsCache(private val database: AppDatabase) :
    IFriendsCache {

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

        Single.create<List<VkUser>> { emitter ->
            database.friendDao.getFriendListById(userId)?.let { roomVkFriendList ->
                val vkFriendList = roomVkFriendList.map {
                    VkUser(
                        it.friendId,
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
        }.subscribeOn(Schedulers.io())

}