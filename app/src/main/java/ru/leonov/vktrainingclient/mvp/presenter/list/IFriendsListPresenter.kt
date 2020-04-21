package ru.leonov.vktrainingclient.mvp.presenter.list

import ru.leonov.vktrainingclient.mvp.view.list.IFriendItemView

interface IFriendsListPresenter {
    var itemClickListener: ((IFriendItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: IFriendItemView)
}