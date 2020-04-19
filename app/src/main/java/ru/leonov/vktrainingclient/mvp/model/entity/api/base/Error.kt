package ru.leonov.vktrainingclient.mvp.model.entity.api.base

import com.google.gson.annotations.Expose

//"error": {
//    "error_code": 113,
//    "error_msg": "Invalid user id",

data class Error (
    @Expose val errorCode: Int,
    @Expose val errorMsg: String
)