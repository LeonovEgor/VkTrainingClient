package ru.leonov.vktrainingclient.mvp.model.entity.room.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.leonov.vktrainingclient.mvp.model.entity.VkUser

interface IFriendsCache {
    fun insertOrReplace(userId: Int, friendList: List<VkUser>): Completable
    fun getFriendListById(userId: Int): Single<List<VkUser>>
}