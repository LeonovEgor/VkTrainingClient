package ru.leonov.vktrainingclient.mvp.model.entity.api.user

import com.google.gson.annotations.Expose
import ru.leonov.vktrainingclient.mvp.model.entity.api.user.User

data class UserListResponse(
    @Expose val items: List<User>?,
    @Expose val count: Int?
)