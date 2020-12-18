package ru.leonov.vktrainingclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.databinding.FragmentFriendsBinding
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.mvp.presenter.FriendsPresenter
import ru.leonov.vktrainingclient.mvp.view.FriendsView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.BackButtonListener
import ru.leonov.vktrainingclient.ui.adapter.FriendsRVAdapter
import javax.inject.Inject

class FriendsFragment : MvpAppCompatFragment(), FriendsView, BackButtonListener {

    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!

    @InjectPresenter
    lateinit var presenter: FriendsPresenter

    @ProvidePresenter
    fun providePresenter() = FriendsPresenter(
        mainThread()
    ).apply {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private var adapter: FriendsRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvFriends.layoutManager = LinearLayoutManager(context)
        adapter = FriendsRVAdapter(presenter.friendsListPresenter, imageLoader)
        binding.rvFriends.adapter = adapter
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