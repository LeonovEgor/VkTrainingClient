package ru.leonov.vktrainingclient.mvp.model.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.leonov.vktrainingclient.mvp.model.entity.VkPhoto
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomPhoto
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.IPhotosCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase

class PhotosCache(private val database: AppDatabase) : IPhotosCache {

    override fun insertOrReplace(userId: Int, photoList: List<VkPhoto>): Completable =
        Completable.fromAction {
            val roomVkPhotoList = photoList.map {
                    RoomPhoto(
                        userId,
                        it.photoUrl,
                        it.text
                    )
            }
            database.photoDao.insert(roomVkPhotoList)
        }.subscribeOn(Schedulers.io())

    override fun getPhotoListById(userId: Int): Single<List<VkPhoto>> =

        Single.create { emitter ->
            database.photoDao.getPhotoListById(userId)?.let { roomPhotoList ->
                val vkPhotoList = roomPhotoList.map {
                    VkPhoto(
                        it.photoUrl,
                        it.text
                    )
                }
                emitter.onSuccess(vkPhotoList)
            } ?: let {
                emitter.onError(RuntimeException("No such photos in cache"))
            }
        }
}