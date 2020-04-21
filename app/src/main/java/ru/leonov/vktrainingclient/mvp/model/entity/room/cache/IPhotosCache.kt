package ru.leonov.vktrainingclient.mvp.model.entity.room.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.leonov.vktrainingclient.mvp.model.entity.VkPhoto
import ru.leonov.vktrainingclient.mvp.model.entity.VkUser

interface IPhotosCache {
    fun insertOrReplace(userId: Int, photoList: List<VkPhoto>): Completable
    fun getPhotoListById(userId: Int): Single<List<VkPhoto>>
}