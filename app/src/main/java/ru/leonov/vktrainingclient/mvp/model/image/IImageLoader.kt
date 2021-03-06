package ru.leonov.vktrainingclient.mvp.model.image

import ru.leonov.vktrainingclient.mvp.model.cache.IImageCache

interface IImageLoader<T> {
    val cache: IImageCache
    fun loadInto(url: String, container: T)
}