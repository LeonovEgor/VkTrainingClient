package ru.leonov.vktrainingclient.mvp.model.repository


data class ResponseResult<T> (
    val data: T?,
    val errorCode: Int,
    val errorMsg: String
)