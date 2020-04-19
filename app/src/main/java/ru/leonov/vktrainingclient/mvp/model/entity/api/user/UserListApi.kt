package ru.leonov.vktrainingclient.mvp.model.entity.api.user

import com.google.gson.annotations.Expose
import ru.leonov.vktrainingclient.mvp.model.entity.api.base.Error
import ru.leonov.vktrainingclient.mvp.model.entity.api.user.UserListResponse

data class UserListApi (
    @Expose val response: UserListResponse?,
    @Expose val error: Error?
)