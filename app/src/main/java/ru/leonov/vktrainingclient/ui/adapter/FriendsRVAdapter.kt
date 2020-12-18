package ru.leonov.vktrainingclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.databinding.FriendItemBinding
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.mvp.presenter.list.IFriendsListPresenter
import ru.leonov.vktrainingclient.mvp.view.list.IFriendItemView

class FriendsRVAdapter(
    val presenter: IFriendsListPresenter,
    private val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<FriendsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent, false), imageLoader)

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    class ViewHolder(
        val containerView: View,
        private val imageLoader: IImageLoader<ImageView>
    ): RecyclerView.ViewHolder(containerView), IFriendItemView {

        override var pos = -1

        private val binding = FriendItemBinding.bind(containerView)

        override fun loadPhoto(url: String) {
            imageLoader.loadInto(url, binding.ivPhoto)
        }

        override fun setFio(fio: String) {
            binding.tvFio.text = fio
        }

        override fun setCity(city: String) {
            binding.tvCityCountry.text = city
        }
    }

}