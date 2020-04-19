package ru.leonov.vktrainingclient.mvp.model.entity.api.photo

import com.google.gson.annotations.Expose

data class Photo (
	@Expose val id : Int,
	@Expose val type: String,
	@Expose val url : String,
	@Expose val width : Int,
	@Expose val height : Int
)