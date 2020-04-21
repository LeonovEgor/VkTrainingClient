package ru.leonov.vktrainingclient.mvp.view.list

interface IPhotosItemView {
    var pos: Int

    fun loadPhoto(url: String)
}