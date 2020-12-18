package ru.leonov.vktrainingclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.databinding.FragmentPhotosBinding
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.mvp.presenter.PhotosPresenter
import ru.leonov.vktrainingclient.mvp.view.PhotosView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.BackButtonListener
import ru.leonov.vktrainingclient.ui.adapter.PhotosRVAdapter
import javax.inject.Inject

class PhotosFragment : MvpAppCompatFragment(), PhotosView, BackButtonListener {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    @InjectPresenter
    lateinit var presenter: PhotosPresenter

    @ProvidePresenter
    fun providePresenter() = PhotosPresenter(AndroidSchedulers.mainThread()).apply {
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
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        adapter = PhotosRVAdapter(presenter.photoListPresenter, imageLoader)
        binding.rvPhotos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


    override fun clearError() {
        binding.tvStatus.text = ""
    }

    override fun showError(error: String) {
        binding.tvStatus.text = error
    }

    override fun showState(state: String) {
        binding.tvStatus.text = state
    }

    override fun backClicked() = presenter.backClicked()

}