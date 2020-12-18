package ru.leonov.vktrainingclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.friend_item.view.*
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.mvp.presenter.list.IPhotoListPresenter
import ru.leonov.vktrainingclient.mvp.view.list.IPhotosItemView

class PhotosRVAdapter(val presenter: IPhotoListPresenter, private val imageLoader: IImageLoader<ImageView>) : RecyclerView.Adapter<PhotosRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false), imageLoader)

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    class ViewHolder(
        override val containerView: View,
        private val imageLoader: IImageLoader<ImageView>
    ): RecyclerView.ViewHolder(containerView), LayoutContainer, IPhotosItemView {

        override var pos = -1

        override fun loadPhoto(url: String)  = with(containerView) {
            imageLoader.loadInto(url, iv_photo)
        }
    }

}