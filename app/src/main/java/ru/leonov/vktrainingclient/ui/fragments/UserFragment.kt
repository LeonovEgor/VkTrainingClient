package ru.leonov.vktrainingclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.databinding.FragmentUserBinding
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.mvp.presenter.UserPresenter
import ru.leonov.vktrainingclient.mvp.view.UserView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.BackButtonListener
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val VK_USER_ID = "vk_user_id"

        // Для отображения друга вместо авторизованного пользователя, при переходе с фрагметна friends
        fun newInstance(userId: Int) = UserFragment().apply {
            arguments = Bundle().apply {
                putInt(VK_USER_ID, userId)
            }
        }
    }

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

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
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {

    }

    override fun clearError() {
        binding.tvStatus.visibility = View.GONE
        binding.tvStatus.text = ""
    }

    override fun showError(error: String) {
        binding.tvStatus.visibility = View.VISIBLE
        binding.tvStatus.text = error
    }

    override fun setUserName(userName: String) {
        binding.tvName.text = userName
    }

    override fun loadPhoto(url: String) {
        imageLoader.loadInto(url, binding.ivUserImage)
    }

    override fun setCity(city: String) {
        binding.tvCityCountry.text = city
    }

    override fun setBirthday(date: String) {
        binding.tvBdate.text = date
    }

    override fun backClicked() = presenter.backClicked()

}