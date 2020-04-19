package ru.geekbrains.poplib.mvp.view.list

interface IPhotosItemView {
    var pos: Int

    fun loadPhoto(url: String)
}