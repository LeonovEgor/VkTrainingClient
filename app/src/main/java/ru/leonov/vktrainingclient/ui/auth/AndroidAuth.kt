package ru.leonov.vktrainingclient.ui.auth

import android.annotation.SuppressLint
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.leonov.vktrainingclient.mvp.model.auth.IAuth
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.utils.getParameter


class AndroidAuth(private val redirectUri: String) : IAuth {

    private val accessTokenParamName = "access_token"
    private val userIdParamName = "id"
    private val errorParamName = "error_msg"

    private lateinit var webView: WebView
    private val subject = PublishSubject.create<UserSession>()


    @SuppressLint("SetJavaScriptEnabled")
    fun setWebView(webView: WebView) {
        this.webView = webView

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let {
                    parseAuthAnswer(it)
                }
                return false
            }
        }
    }

    private fun parseAuthAnswer(url: String) {
        if (!url.startsWith(redirectUri)) return

        val token = url.getParameter(accessTokenParamName)
        val userId = url.getParameter(userIdParamName)

        if (token != null && userId != null) {
            subject.onNext(UserSession(token, userId.toInt()))
        } else {
            subject.onError(
                Throwable(url.getParameter(errorParamName) ?: "Unknown auth error!!!")
            )
        }
    }

    override fun login(url: String): Single<UserSession> =
        subject.first(UserSession()).apply {
            webView.loadUrl(url)
        }.subscribeOn(AndroidSchedulers.mainThread())

    override fun logout() {
        clearCookies()
    }

    // Другого способа через API нет.
    private fun clearCookies() {
        CookieManager.getInstance().removeAllCookies(null)
        CookieManager.getInstance().flush()
    }
}