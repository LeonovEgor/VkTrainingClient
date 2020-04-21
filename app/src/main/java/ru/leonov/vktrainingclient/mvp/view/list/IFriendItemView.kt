package ru.leonov.vktrainingclient.mvp.view.list

interface IFriendItemView {
    var pos: Int

    fun loadPhoto(url: String)
    fun setFio(fio: String)
    fun setCity(city: String)
}