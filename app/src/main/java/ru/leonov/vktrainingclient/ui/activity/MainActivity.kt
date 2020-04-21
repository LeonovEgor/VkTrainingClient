package ru.leonov.vktrainingclient.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.mvp.presenter.MainPresenter
import ru.leonov.vktrainingclient.mvp.view.MainView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.BackButtonListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        private const val VK_TOKEN = "vktoken"
        private const val VK_USER_ID = "vkuserid"

        fun start(context: Context, token: String, userId: Int) = Intent(context, MainActivity::class.java).apply {
            this.putExtra(VK_TOKEN, token)
            this.putExtra(VK_USER_ID, userId)
            context.startActivity(this)
        }
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = SupportAppNavigator(this, R.id.container)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = intent.getIntExtra(VK_USER_ID, 0)
        intent.getStringExtra(VK_TOKEN)?.let {
            presenter.onTokenChanged(it, userId)
        }

        App.instance.appComponent.inject(this)

        navigationViewInit()
    }

    private fun navigationViewInit() {
        bottom_navigation.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.action_main -> { presenter.onHomeClick() }
                R.id.action_friends -> { presenter.onFriendsClick() }
                R.id.action_photos -> { presenter.onPhotosClick() }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    @ProvidePresenter
    fun providePresenter() = MainPresenter().apply {
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backClicked()){
                return
            }
        }
        presenter.backClicked()
    }

    override fun init() {

    }
}
