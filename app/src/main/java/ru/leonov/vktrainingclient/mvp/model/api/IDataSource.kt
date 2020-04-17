package ru.geekbrains.poplib.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import ru.leonov.vktrainingclient.mvp.model.entity.api.UsersApi

interface IDataSource {

//https://api.vk.com/method/users.get?user_ids=210700286&fields=bdate&access_token=533bacf01e11f55b536a565b57531ac114461ae8736d6506a3&v=5.103

//    @GET("/users/{user}")
//    fun getUser(@Path("user") username: String): Single<GithubUser>

    @GET("/method/users.get")
    fun getUser(
        @Query("user_ids") userId: Int,
        @Query("fields") fields: String,
        @Query("name_case") nameCase: String,
        @Query("access_token") token: String,
        @Query("v") version: String
    ): Single<UsersApi>

//    @GET
//    fun getRepos(@Url url: String): Single<List<GithubRepository>>

}