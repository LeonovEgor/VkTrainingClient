package ru.leonov.vktrainingclient.ui.auth

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.repository.IAuthRepo
import ru.leonov.vktrainingclient.mvp.utils.getParameter

class AndroidAuthRepo(private val redirectUri: String) : IAuthRepo {

    private val accessTokenParamName = "access_token"
    private val userIdParamName = "id"
    private val errorParamName = "error_msg"


    private lateinit var webView: WebView
    private var userSessionLiveData = MutableLiveData<UserSession>()

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebView(webView: WebView) {
        this.webView = webView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let {
                    isAuthAnswer(it)?.let { userSession->
                        userSessionLiveData.setValue(userSession)
                    }
                }
                return false
            }
        }
    }

    override fun login(url: String) {
        webView.loadUrl(url)
    }

    fun isAuthAnswer(url: String): UserSession? =
        if (url.contains(redirectUri)) {
            val token = url.getParameter(accessTokenParamName)
            val userId = url.getParameter(userIdParamName)
            val error = url.getParameter(errorParamName)
            UserSession(token, userId, error)
        } else null

    override fun getUserSession(): LiveData<UserSession> = userSessionLiveData
}