package ru.leonov.vktrainingclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import kotlinx.android.synthetic.main.activity_auth.tv_status
import kotlinx.android.synthetic.main.fragment_friends.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.mvp.presenter.FriendsPresenter
import ru.leonov.vktrainingclient.mvp.view.FriendsView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.BackButtonListener
import ru.leonov.vktrainingclient.ui.adapter.FriendsRVAdapter
import javax.inject.Inject

class FriendsFragment : MvpAppCompatFragment(), FriendsView, BackButtonListener {

    @InjectPresenter
    lateinit var presenter: FriendsPresenter

    @ProvidePresenter
    fun providePresenter() = FriendsPresenter(
        mainThread()).apply {
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_friends, container, false)

    override fun init() {
        rv_friends.layoutManager = LinearLayoutManager(context)
        adapter = FriendsRVAdapter(presenter.friendsListPresenter, imageLoader)
        rv_friends.adapter = adapter
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
