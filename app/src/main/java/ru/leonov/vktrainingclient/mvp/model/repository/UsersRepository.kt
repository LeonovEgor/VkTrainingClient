package ru.leonov.vktrainingclient.mvp.model.repository

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.leonov.vktrainingclient.mvp.model.entity.api.user.User
import ru.leonov.vktrainingclient.mvp.model.entity.api.user.UserListResponse

class UsersRepository(
    private val api: IDataSource
//    private val networkStatus: NetworkStatus,
//    private val cache: IRepositoriesCache
) {
    private val VERSION = "5.103"

    fun getUser(usersId: Int, fields: String, token: String): @NonNull Single<ResponseResult<User>> =
        api.getUser(usersId, fields, token, VERSION)
            .map {usersApi->
                ResponseResult( usersApi.response?.first(),
                    usersApi.error?.errorCode ?: 0,
                    usersApi.error?.errorMsg ?: "")
        }.subscribeOn(Schedulers.io())

    fun getUserList(userId: Int, fields: String, token: String): @NonNull Single<ResponseResult<UserListResponse>> =
        api.getSubscriptions( userId, fields, token, VERSION)
            .map {userListApi->
                ResponseResult( userListApi.response,
                userListApi.error?.errorCode ?: 0,
                userListApi.error?.errorMsg ?: "")
            }.subscribeOn(Schedulers.io())
}