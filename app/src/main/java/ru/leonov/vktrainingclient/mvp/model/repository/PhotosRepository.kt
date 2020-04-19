package ru.leonov.vktrainingclient.mvp.model.repository

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.leonov.vktrainingclient.mvp.model.entity.api.photo.PhotoListResponse


class PhotosRepository(
    private val api: IDataSource
//    private val networkStatus: NetworkStatus,
//    private val cache: IRepositoriesCache
) {
    private val VERSION = "5.103"

    fun getPhotosList(ownerId: Int, album_id: String, count: Int, token: String): @NonNull Single<ResponseResult<PhotoListResponse>> =
        api.getPhotos( ownerId, album_id, count, token, VERSION)
            .map {photoListApi->
                ResponseResult( photoListApi.response,
                    photoListApi.error?.errorCode ?: 0,
                    photoListApi.error?.errorMsg ?: "")
            }.subscribeOn(Schedulers.io())
}