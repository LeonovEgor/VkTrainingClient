package ru.leonov.vktrainingclient.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.entity.VkUser
import ru.leonov.vktrainingclient.mvp.model.repository.FriendsRepository
import ru.leonov.vktrainingclient.mvp.presenter.list.IFriendsListPresenter
import ru.leonov.vktrainingclient.mvp.view.FriendsView
import ru.leonov.vktrainingclient.mvp.view.list.IFriendItemView
import ru.leonov.vktrainingclient.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class FriendsPresenter(
    private val mainThreadScheduler: Scheduler) : MvpPresenter<FriendsView>() {

    private val fields = "photo_200, country, city"

    @Inject
    lateinit var friendsRepo: FriendsRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userSession: UserSession

    class FriendsListPresenter : IFriendsListPresenter {
        val friends = mutableListOf<VkUser>()
        override var itemClickListener: ((IFriendItemView) -> Unit)? = null

        override fun getCount() = friends.size

        override fun bindView(view: IFriendItemView) {
            val friend = friends[view.pos]
            view.setFio(friend.name)
            view.setCity(friend.cityCountry)
            view.loadPhoto(friend.photoUrl)
        }
    }

    val friendsListPresenter = FriendsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadUserList()

        friendsListPresenter.itemClickListener = { itemView ->
            val friend = friendsListPresenter.friends[itemView.pos]
            router.navigateTo(Screens.UserScreen(friend.userId))
        }
    }

    private fun loadUserList() {
        viewState.clearError()

        friendsRepo.getFriendList(userSession.userId, fields, userSession.token)
            .observeOn(mainThreadScheduler)
            .subscribe ( { friendList ->
                    viewState.showState("Friends: ${friendList.count()}")
                    friendsListPresenter.friends.clear()
                    friendsListPresenter.friends.addAll(friendList)
                    viewState.updateList()
            }, {
              viewState.showError(it.localizedMessage ?: "unknown error")
            })
    }

    fun backClicked() : Boolean {
        router.exit()
        return true
    }

}