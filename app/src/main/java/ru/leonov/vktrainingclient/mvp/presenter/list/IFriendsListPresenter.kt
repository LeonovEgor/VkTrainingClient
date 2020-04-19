package ru.geekbrains.poplib.mvp.presenter.list

import ru.geekbrains.poplib.mvp.view.list.IFriendItemView

interface IFriendsListPresenter {
    var itemClickListener: ((IFriendItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: IFriendItemView)
}