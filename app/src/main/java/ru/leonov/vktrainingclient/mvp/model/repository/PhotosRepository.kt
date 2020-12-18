package ru.leonov.vktrainingclient.mvp.model.repository

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.leonov.vktrainingclient.mvp.model.api.IDataSource
import ru.leonov.vktrainingclient.mvp.model.entity.VkPhoto
import ru.leonov.vktrainingclient.mvp.model.cache.IPhotosCache
import ru.leonov.vktrainingclient.ui.network.NetworkStatus

class PhotosRepository(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: IPhotosCache
) {
    private val version = "5.103"

    fun getPhotoList(
        ownerId: Int,
        album_id: String,
        count: Int,
        token: String
    ): @NonNull Single<List<VkPhoto>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getPhotos(ownerId, album_id, count, token, version).flatMap { photoListApi ->
                    val vkPhotoList = photoListApi.response.items?.map { photo ->
                        VkPhoto(
                            photo.sizes[photo.sizes.lastIndex].url,
                            photo.text
                        )
                    } ?: ArrayList()
                    cache.insertOrReplace(ownerId, vkPhotoList).toSingleDefault(vkPhotoList)
                }
            } else {
                cache.getPhotoListById(ownerId)
            }
        }.subscribeOn(Schedulers.io())

}