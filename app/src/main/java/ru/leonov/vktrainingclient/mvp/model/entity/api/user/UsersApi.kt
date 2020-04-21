package ru.leonov.vktrainingclient.mvp.model.entity.api.user

import com.google.gson.annotations.Expose
import ru.leonov.vktrainingclient.mvp.model.entity.api.base.User

data class UsersApi ( @Expose val response : List<User> )
