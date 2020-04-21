package ru.leonov.vktrainingclient.mvp.presenter.list

import ru.leonov.vktrainingclient.mvp.view.list.IPhotosItemView


interface IPhotoListPresenter {
    fun getCount(): Int
    fun bindView(view: IPhotosItemView)
}