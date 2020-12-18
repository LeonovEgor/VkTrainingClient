package ru.leonov.vktrainingclient.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.presenter.MainPresenter
import ru.leonov.vktrainingclient.mvp.view.MainView
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.BackButtonListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = SupportAppNavigator(this, R.id.container)

    @Inject
    lateinit var userSession: UserSession

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = MainPresenter().apply {
        App.instance.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                presenter.onLogout()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun goToLogout() {
        AuthActivity.start(this, true)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
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