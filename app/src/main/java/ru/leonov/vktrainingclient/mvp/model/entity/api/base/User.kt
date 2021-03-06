package ru.leonov.vktrainingclient.mvp.model.entity.api.base

import com.google.gson.annotations.Expose

data class User (
    @Expose val id : Int,
    @Expose val first_name : String,
    @Expose val last_name : String,
    @Expose val bdate : String?,
    @Expose val city : City?,
    @Expose val country : Country?,
    @Expose val photo_200 : String
)