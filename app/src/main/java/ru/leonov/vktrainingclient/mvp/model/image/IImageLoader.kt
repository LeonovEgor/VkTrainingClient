package ru.geekbrains.poplib.mvp.model.image

interface IImageLoader<T> {
    //val cache: IImageCache
    fun loadInto(url: String, container: T)
}