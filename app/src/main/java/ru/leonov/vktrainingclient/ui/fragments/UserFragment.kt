package ru.leonov.vktrainingclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import kotlinx.android.synthetic.main.activity_auth.tv_status
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.mvp.presenter.UserPresenter
import ru.leonov.vktrainingclient.mvp.view.UserView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.BackButtonListener
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val VK_USER_ID = "vkuserid"

        // Для отображения друга вместо авторизованного пользователя, при переходе с фрагметна friends
        fun newInstance(userId: Int) = UserFragment().apply {
            arguments = Bundle().apply {
                putInt(VK_USER_ID, userId)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: UserPresenter

    @ProvidePresenter
    fun providePresenter() = UserPresenter(
        mainThread(),
        arguments!![VK_USER_ID] as Int).apply {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user, container, false)

    override fun init() {

    }

    override fun clearError() {
        tv_status.visibility = View.GONE
        tv_status.text = ""
    }

    override fun showError(error: String) {
        tv_status.visibility = View.VISIBLE
        tv_status.text = error
    }

    override fun setUserName(userName: String) {
        tv_name.text = userName
    }

    override fun loadPhoto(url: String) {
        imageLoader.loadInto(url, iv_user_image)
    }

    override fun setCity(city: String) {
        tv_city_country.text = city
    }

    override fun setBirthday(date: String) {
        tv_bdate.text = date
    }

    override fun backClicked() = presenter.backClicked()
}
