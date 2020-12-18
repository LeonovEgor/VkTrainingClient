package ru.leonov.vktrainingclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.databinding.PhotoItemBinding
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.mvp.presenter.list.IPhotoListPresenter
import ru.leonov.vktrainingclient.mvp.view.list.IPhotosItemView

class PhotosRVAdapter(
    val presenter: IPhotoListPresenter,
    private val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<PhotosRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false),
            imageLoader
        )

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    class ViewHolder(
        containerView: View,
        private val imageLoader: IImageLoader<ImageView>
    ) : RecyclerView.ViewHolder(containerView), IPhotosItemView {

        private val binding = PhotoItemBinding.bind(containerView)

        override var pos = -1

        override fun loadPhoto(url: String) = imageLoader.loadInto(url, binding.ivPhoto)
    }

}