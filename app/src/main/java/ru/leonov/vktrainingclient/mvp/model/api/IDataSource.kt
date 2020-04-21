package ru.leonov.vktrainingclient.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.leonov.vktrainingclient.mvp.model.entity.api.photo.PhotoListApi
import ru.leonov.vktrainingclient.mvp.model.entity.api.user.UsersApi
import ru.leonov.vktrainingclient.mvp.model.entity.api.friends.FriendListApi

interface IDataSource {

    @GET("/method/users.get")
    fun getUser(
        @Query("user_ids") userId: Int,
        @Query("fields") fields: String,
        @Query("access_token") token: String,
        @Query("v") version: String
    ): Single<UsersApi>

    @GET("/method/friends.get")
    fun getFriends(
        @Query("user_id") userId: Int,
        @Query("fields") fields: String,
        @Query("access_token") token: String,
        @Query("v") version: String
    ): Single<FriendListApi>

    @GET("/method/photos.get")
    fun getPhotos(
        @Query("owner_id") owner_id: Int,
        @Query("album_id") album_id: String,
        @Query("count") count: Int,
        @Query("access_token") token: String,
        @Query("v") version: String
    ): Single<PhotoListApi>

}