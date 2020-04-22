package ru.leonov.vktrainingclient.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_auth.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.mvp.model.entity.IAuthCallback
import ru.leonov.vktrainingclient.mvp.model.repository.IAuthRepo
import ru.leonov.vktrainingclient.mvp.presenter.AuthPresenter
import ru.leonov.vktrainingclient.mvp.view.AuthView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.auth.AndroidAuthRepo
import javax.inject.Inject

class AuthActivity : MvpAppCompatActivity(), AuthView, IAuthCallback {

    companion object {
        private const val logout_text = "logout"

        fun start(context: Context, isLogout: Boolean) = Intent(context, AuthActivity::class.java).apply {
            this.putExtra(logout_text, isLogout)
            context.startActivity(this)
        }
    }

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @Inject
    lateinit var authRepo: IAuthRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        App.instance.appComponent.inject(this)
        initAuthRepo()

        val isLogout = intent.getBooleanExtra(logout_text, false)
        if (isLogout) presenter.logout()
        else presenter.login()
    }

    private fun initAuthRepo() {
        if (authRepo is AndroidAuthRepo)
            (authRepo as AndroidAuthRepo).setWebView(web_view)
    }

    @ProvidePresenter
    fun providePresenter() = AuthPresenter().apply {
        App.instance.appComponent.inject(this)
    }

    override fun login(url: String) {
        authRepo.login(url, this)
    }

    override fun logout() {
        authRepo.logout()
    }

    override fun init() {

    }

    override fun goToNextActivity(token: String, userId: Int) {
        MainActivity.start(this)
        finish()
    }

    override fun showError(error: String) {
        tv_status.text = error
    }

    override fun onUserAuthorized(token: String, userId: Int) {
        presenter.onUserAuthorized(token, userId)
    }

    override fun OnUserAuthorizedError(error: String) {
        presenter.onUserAuthorizedError(error)
    }
}