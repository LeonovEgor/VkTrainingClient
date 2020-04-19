package ru.leonov.vktrainingclient.mvp.model.entity.api.photo

import com.google.gson.annotations.Expose
import ru.leonov.vktrainingclient.mvp.model.entity.api.base.Error
import ru.leonov.vktrainingclient.mvp.model.entity.api.user.UserListResponse

data class PhotoListApi (
    @Expose val response: PhotoListResponse?,
    @Expose val error: Error?
)