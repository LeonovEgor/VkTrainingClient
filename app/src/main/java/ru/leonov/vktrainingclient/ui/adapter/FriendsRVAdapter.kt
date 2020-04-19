package ru.geekbrains.poplib.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.friend_item.view.*
import ru.geekbrains.poplib.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.R
import ru.geekbrains.poplib.mvp.presenter.list.IFriendsListPresenter
import ru.geekbrains.poplib.mvp.view.list.IFriendItemView

class FriendsRVAdapter(val presenter: IFriendsListPresenter, val imageLoader: IImageLoader<ImageView>) : RecyclerView.Adapter<FriendsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent, false), imageLoader)

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    class ViewHolder(
        override val containerView: View,
        private val imageLoader: IImageLoader<ImageView>
    ): RecyclerView.ViewHolder(containerView), LayoutContainer, IFriendItemView {

        override var pos = -1

        override fun loadPhoto(url: String)  = with(containerView) {
            imageLoader.loadInto(url, iv_photo)
        }

        override fun setFio(fio: String) = with(containerView) {
            tv_fio.text = fio
        }

        override fun setCity(city: String) = with(containerView) {
            tv_city_country.text = city
        }
    }
}