package ru.leonov.vktrainingclient.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_auth.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.mvp.model.repository.IAuthRepo
import ru.leonov.vktrainingclient.mvp.presenter.AuthPresenter
import ru.leonov.vktrainingclient.mvp.view.AuthView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.auth.AndroidAuthRepo
import javax.inject.Inject


class AuthActivity : MvpAppCompatActivity(), AuthView {

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @Inject
    lateinit var authRepo: IAuthRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        App.instance.appComponent.inject(this)
        initAuthRepo()
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

        authRepo.getUserSession().observe( this, Observer{
            presenter.authResponse(it)
        })
        authRepo.login(url)
        //web_view.loadUrl(url)
    }

    override fun init() {

    }

    override fun goToNextActivity(tocken: String, userId: Int) {
        MainActivity.start(this, tocken, userId)
        finish()
    }

    override fun showError(error: String) {
        tv_status.text = error
    }

//    @SuppressLint("SetJavaScriptEnabled")
//    override fun init() {
//        web_view.settings.javaScriptEnabled = true
//        val webViewClient = object : WebViewClient() {
//
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                presenter.onAuthResponse(url!!)
//                return false
//            }
//        }
//        web_view.webViewClient = webViewClient
//    }
}