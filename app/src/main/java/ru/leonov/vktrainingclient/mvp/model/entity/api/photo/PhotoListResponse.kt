package ru.leonov.vktrainingclient.mvp.model.entity.api.photo

import com.google.gson.annotations.Expose
import ru.leonov.vktrainingclient.mvp.model.entity.api.photo.PhotoList

data class PhotoListResponse(
    @Expose val items: List<PhotoList>?,
    @Expose val count: Int?
)