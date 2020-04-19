package ru.leonov.vktrainingclient.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.presenter.list.IFriendsListPresenter
import ru.geekbrains.poplib.mvp.view.list.IFriendItemView
import ru.geekbrains.poplib.navigation.Screens
import ru.leonov.vktrainingclient.mvp.model.entity.api.user.User
import ru.leonov.vktrainingclient.mvp.model.repository.UsersRepository
import ru.leonov.vktrainingclient.mvp.utils.concatString
import ru.leonov.vktrainingclient.mvp.view.FriendsView
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class FriendsPresenter(
    private val mainThreadScheduler: Scheduler,
    private val token: String,
    private val userId: Int
) : MvpPresenter<FriendsView>() {

    private val fields = "photo_200, country, city"

    @Inject
    lateinit var userRepo: UsersRepository

    @Inject
    lateinit var router: Router

    class FriendsListPresenter : IFriendsListPresenter {
        val friends = mutableListOf<User>()
        override var itemClickListener: ((IFriendItemView) -> Unit)? = null

        override fun getCount() = friends.size

        override fun bindView(view: IFriendItemView) {
            val friend = friends[view.pos]
            view.setFio("${friend.first_name}, ${friend.last_name}")
            val cityCountry = concatString(friend.city?.title, ", ", friend.country?.title)
            view.setCity(cityCountry)
            view.loadPhoto(friend.photo_200)
        }
    }

    val friendsListPresenter = FriendsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadUserList()

        friendsListPresenter.itemClickListener = { itemView ->
            val friend = friendsListPresenter.friends[itemView.pos]
            router.navigateTo(Screens.UserScreen(token, friend.id))
        }
    }

    private fun loadUserList() {
        viewState.clearError()

        userRepo.getUserList(userId, fields, token)
            .observeOn(mainThreadScheduler)
            .subscribe ( { responseResult ->
                if (responseResult.errorCode != 0) {
                    viewState.showError(responseResult.errorMsg)
                } else {
                    responseResult.data?.let {userListResponce->
                        viewState.showState("Friends: ${userListResponce.count}")
                        friendsListPresenter.friends.clear()
                        userListResponce.items?.let { userList->
                            friendsListPresenter.friends.addAll(userList)
                            viewState.updateList()
                        }
                    }
                }
            }, {
              viewState.showError(it.localizedMessage ?: "unknown error")
            })
    }

    fun backClicked() : Boolean {
        router.exit()
        return true
    }

}
