package ru.leonov.vktrainingclient.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.databinding.ActivityAuthBinding
import ru.leonov.vktrainingclient.mvp.model.auth.IAuth
import ru.leonov.vktrainingclient.mvp.presenter.AuthPresenter
import ru.leonov.vktrainingclient.mvp.view.AuthView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.auth.AndroidAuth
import javax.inject.Inject

class AuthActivity : MvpAppCompatActivity(), AuthView {

    companion object {
        private const val logout_text = "logout"

        fun start(context: Context, isLogout: Boolean) = Intent(context, AuthActivity::class.java).apply {
            this.putExtra(logout_text, isLogout)
            context.startActivity(this)
        }
    }

    private lateinit var binding: ActivityAuthBinding

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @Inject
    lateinit var auth: IAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        App.instance.appComponent.inject(this)

        initAuthRepo()
        logInOut()
    }

    private fun logInOut() {
        val isLogout = intent.getBooleanExtra(logout_text, false)
        if (isLogout) presenter.logout()
        else presenter.login()
    }

    private fun initAuthRepo() {
        if (auth is AndroidAuth)
            (auth as AndroidAuth).setWebView(binding.webView)
    }

    @ProvidePresenter
    fun providePresenter() = AuthPresenter(AndroidSchedulers.mainThread()).apply {
        App.instance.appComponent.inject(this)
    }

    override fun logout() {
        auth.logout()
    }

    override fun init() {

    }

    override fun goToNextActivity(token: String, userId: Int) {
        MainActivity.start(this)
        finish()
    }

    override fun showError(error: String) {
        binding.tvStatus.text = error
    }

}