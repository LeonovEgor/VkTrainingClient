package ru.geekbrains.poplib.mvp.presenter.list

import ru.geekbrains.poplib.mvp.view.list.IPhotosItemView


interface IPhotoListPresenter {
    fun getCount(): Int
    fun bindView(view: IPhotosItemView)
}