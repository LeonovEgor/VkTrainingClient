package ru.leonov.vktrainingclient.mvp.model.entity.api

import com.google.gson.annotations.Expose

//{
//    "response": [{
//    "id": 210700286,
//    "first_name": "Lindsey",
//    "last_name": "Stirling",
//    "is_closed": false,
//    "can_access_closed": true,
//    "bdate": "21.9.1986",
//    "city": {
//    "id": 5331,
//    "title": "Los Angeles"
//},
//    "country": {
//    "id": 9,
//    "title": "США"
//},
//    "photo_50": "https://sun1-19.u...EGxg5NXns.jpg?ava=1"
//}]
//}

data class UsersApi (
    @Expose val response : List<User>?,
    @Expose val error: Error?
)
