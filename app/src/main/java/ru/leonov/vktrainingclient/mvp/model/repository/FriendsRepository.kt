package ru.leonov.vktrainingclient.mvp.model.repository

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.leonov.vktrainingclient.mvp.model.api.IDataSource
import ru.leonov.vktrainingclient.mvp.model.entity.VkUser
import ru.leonov.vktrainingclient.mvp.model.entity.room.cache.IFriendsCache
import ru.leonov.vktrainingclient.mvp.utils.concatString
import ru.leonov.vktrainingclient.ui.network.NetworkStatus

class FriendsRepository(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: IFriendsCache
) {
    private val version = "5.103"

    fun getFriendList(userId: Int, fields: String, token: String): @NonNull Single<List<VkUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getFriends(userId, fields, token, version).flatMap { friendListApi ->
                    val vkFriendList = friendListApi.response.items.map { user ->
                        VkUser(
                            user.id,
                            concatString(user.first_name, " ", user.last_name),
                            "",
                            user.photo_200,
                            concatString(user.city?.title, ", ", user.country?.title)
                        )
                    }
                    cache.insertOrReplace(userId, vkFriendList).toSingleDefault(vkFriendList)
                }
            } else {
                cache.getFriendListById(userId)
            }
        }.subscribeOn(Schedulers.io())
}