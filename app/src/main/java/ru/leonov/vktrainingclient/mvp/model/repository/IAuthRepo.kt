package ru.leonov.vktrainingclient.mvp.model.repository

import androidx.lifecycle.LiveData
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession

interface IAuthRepo {
    fun login(url: String)
    fun logout()
    fun getUserSession(): LiveData<UserSession>
}