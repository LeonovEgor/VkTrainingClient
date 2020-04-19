package ru.leonov.vktrainingclient.mvp.model.entity.api.user

import com.google.gson.annotations.Expose
import ru.leonov.vktrainingclient.mvp.model.entity.api.base.Error
import ru.leonov.vktrainingclient.mvp.model.entity.api.user.User

data class UsersApi (
    @Expose val response : List<User>?,
    @Expose val error: Error?
)
