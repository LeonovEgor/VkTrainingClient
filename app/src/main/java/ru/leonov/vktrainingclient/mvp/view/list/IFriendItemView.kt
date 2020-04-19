package ru.geekbrains.poplib.mvp.view.list

interface IFriendItemView {
    var pos: Int

    fun loadPhoto(url: String)
    fun setFio(fio: String)
    fun setCity(city: String)
}