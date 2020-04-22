package ru.leonov.vktrainingclient.mvp.model.entity

interface IAuthCallback {
    fun onUserAuthorized(token: String, userId: Int)
    fun OnUserAuthorizedError(error: String)
}