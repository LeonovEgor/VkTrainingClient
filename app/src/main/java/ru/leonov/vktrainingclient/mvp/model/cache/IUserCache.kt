package ru.leonov.vktrainingclient.mvp.model.cache

import io.reactivex.rxjava3.core.Single
import ru.leonov.vktrainingclient.mvp.model.entity.VkUser

interface IUserCache {
    fun insertOrReplace(user: VkUser)
    fun getUserById(userId: Int): Single<VkUser>
}