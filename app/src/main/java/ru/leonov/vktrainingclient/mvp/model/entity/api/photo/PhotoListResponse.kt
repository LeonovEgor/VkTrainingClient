package ru.leonov.vktrainingclient.mvp.model.entity.api.photo

import com.google.gson.annotations.Expose

data class PhotoListResponse(
    @Expose val items: List<Photo>?,
    @Expose val count: Int?
)