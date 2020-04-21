package ru.leonov.vktrainingclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_photos.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.mvp.presenter.PhotosPresenter
import ru.leonov.vktrainingclient.mvp.view.PhotosView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.BackButtonListener
import ru.leonov.vktrainingclient.ui.adapter.PhotosRVAdapter
import javax.inject.Inject

class PhotosFragment : MvpAppCompatFragment(), PhotosView, BackButtonListener {

    companion object {
        //TODO: Заменить на Inject
        private const val VK_TOKEN = "vktoken"
        private const val VK_USER_ID = "vkuserid"

        fun newInstance(token: String, userId: Int) = PhotosFragment().apply {
            arguments = Bundle().apply {
                putString(VK_TOKEN, token)
                putInt(VK_USER_ID, userId)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: PhotosPresenter

    @ProvidePresenter
    fun providePresenter() = PhotosPresenter(
        AndroidSchedulers.mainThread(),
        arguments!![VK_TOKEN] as String,
        arguments!![VK_USER_ID] as Int).apply {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private var adapter: PhotosRVAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_photos, container, false)

    override fun init() {
        rv_photos.layoutManager = GridLayoutManager(context, 2)
        adapter = PhotosRVAdapter(presenter.photoListPresenter, imageLoader)
        rv_photos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


    override fun clearError() {
        tv_status.text = ""
    }

    override fun showError(error: String) {
        tv_status.text = error
    }

    override fun showState(state: String) {
        tv_status.text = state
    }

    override fun backClicked() = presenter.backClicked()
}
