package ru.leonov.vktrainingclient.ui.auth

import android.annotation.SuppressLint
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.leonov.vktrainingclient.mvp.model.entity.IAuthCallback
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.repository.IAuthRepo
import ru.leonov.vktrainingclient.mvp.utils.getParameter


class AndroidAuthRepo(private val redirectUri: String) : IAuthRepo {

    private val accessTokenParamName = "access_token"
    private val userIdParamName = "id"
    private val errorParamName = "error_msg"

    private lateinit var webView: WebView
    private lateinit var callback: IAuthCallback

    //var userSessionLiveData = MutableLiveData<UserSession>()
    //var errorLiveData = MutableLiveData<String>()

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebView(webView: WebView) {
        this.webView = webView

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { parseAuthAnswer(it) }
                return false
            }
        }
    }

    override fun login(url: String, callback: IAuthCallback) {
        this.callback = callback
        webView.loadUrl(url)
    }

    fun parseAuthAnswer(url: String) {
        if (url.contains(redirectUri)) {
            url.getParameter(errorParamName)?.let {
                callback.OnUserAuthorizedError(errorParamName)
            } ?: let { userSession ->
                val token = url.getParameter(accessTokenParamName)
                val userId = url.getParameter(userIdParamName)
                if (token != null && userId != null) {
                    callback.onUserAuthorized(token, userId.toInt())
                } else {
                    callback.OnUserAuthorizedError("Unknown error!!!")
                }
                //userSessionLiveData.setValue()
            }
//            val token = url.getParameter(accessTokenParamName)
//            val userId = url.getParameter(userIdParamName)
//            val error = url.getParameter(errorParamName)
//            UserSession(token, userId, error)
        }
    }

    //override fun getUserSession(): LiveData<UserSession> = userSessionLiveData

    override fun logout() {
        clearCookies()
    }

    private fun clearCookies() {
        CookieManager.getInstance().removeAllCookies(null)
        CookieManager.getInstance().flush()
    }
}