package ru.leonov.vktrainingclient.mvp.model.repository

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.leonov.vktrainingclient.mvp.model.entity.UserRequestData

class UsersRepository(
    private val api: IDataSource
//    private val networkStatus: NetworkStatus,
//    private val cache: IRepositoriesCache
) {
    private val VERSION = "5.103"

    fun getUser(data: UserRequestData): @NonNull Single<UserResult> =
        api.getUser(data.usersId, data.fields, data.nameCase.toString(), data.token, VERSION)
            .map {usersApi->
                UserResult( usersApi.response?.first(),
                    usersApi.error?.errorCode ?: 0,
                    usersApi.error?.errorMsg ?: "")
        }.subscribeOn(Schedulers.io())
}