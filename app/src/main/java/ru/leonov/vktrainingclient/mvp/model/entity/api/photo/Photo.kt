package ru.leonov.vktrainingclient.mvp.model.entity.api.photo

import com.google.gson.annotations.Expose
import ru.leonov.vktrainingclient.mvp.model.entity.api.base.Photo

data class Photo (
	@Expose val id : Int,
	@Expose val sizes: List<Photo>,
	@Expose val text : String
)