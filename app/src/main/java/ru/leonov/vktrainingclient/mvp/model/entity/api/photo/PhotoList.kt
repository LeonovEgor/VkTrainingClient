package ru.leonov.vktrainingclient.mvp.model.entity.api.photo

import com.google.gson.annotations.Expose
import java.util.*

data class PhotoList (
	@Expose val id : Int,
	@Expose val sizes: List<Photo>,
	@Expose val text : String
	//@Expose val date : Date
)