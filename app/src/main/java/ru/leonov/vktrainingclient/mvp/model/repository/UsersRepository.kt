package ru.leonov.vktrainingclient.mvp.model.repository

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.leonov.vktrainingclient.mvp.model.api.IDataSource
import ru.leonov.vktrainingclient.mvp.model.entity.VkUser
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.IUserCache
import ru.leonov.vktrainingclient.mvp.utils.concatString
import ru.leonov.vktrainingclient.ui.network.NetworkStatus
import java.util.*

class UsersRepository(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: IUserCache
) {
    private val version = "5.103"

    fun getUser(usersId: Int, fields: String, token: String): @NonNull Single<VkUser> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUser(usersId, fields, token, version)
                    .map { usersApi ->
                        val user = usersApi.response[0]
                        val vkUser = VkUser(
                            user.id,
                            concatString(user.first_name, " ", user.last_name),
                            user.bdate ?: "",
                            user.photo_200,
                            concatString(user.city?.title, ", ", user.country?.title)
                        )
                        cache.insertOrReplace(vkUser)
                        return@map vkUser
                    }
            } else {
                cache.getUserById(usersId)
            }
        }.subscribeOn(Schedulers.io())
}