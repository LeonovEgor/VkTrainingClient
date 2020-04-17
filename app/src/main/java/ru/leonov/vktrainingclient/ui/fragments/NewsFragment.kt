package ru.leonov.vktrainingclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import ru.leonov.vktrainingclient.R
import ru.leonov.vktrainingclient.mvp.view.NewsView

class NewsFragment : MvpAppCompatFragment(), NewsView {


    companion object {
        fun newInstance() = NewsFragment()
    }

    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun init() {

    }

    override fun clearError() {
        TODO("Not yet implemented")
    }

    override fun showError(error: String) {
        TODO("Not yet implemented")
    }
}
