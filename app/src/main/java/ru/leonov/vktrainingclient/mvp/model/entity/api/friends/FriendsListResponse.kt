package ru.leonov.vktrainingclient.mvp.model.entity.api.friends

import com.google.gson.annotations.Expose
import ru.leonov.vktrainingclient.mvp.model.entity.api.base.User

data class FriendsListResponse(
    @Expose val items: List<User>,
    @Expose val count: Int
)