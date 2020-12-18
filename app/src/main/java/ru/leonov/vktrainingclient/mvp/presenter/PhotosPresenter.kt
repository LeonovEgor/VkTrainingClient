package ru.leonov.vktrainingclient.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.leonov.vktrainingclient.mvp.model.entity.UserSession
import ru.leonov.vktrainingclient.mvp.model.entity.VkPhoto
import ru.leonov.vktrainingclient.mvp.model.entity.api.photo.Photo
import ru.leonov.vktrainingclient.mvp.model.repository.PhotosRepository
import ru.leonov.vktrainingclient.mvp.presenter.list.IPhotoListPresenter
import ru.leonov.vktrainingclient.mvp.view.PhotosView
import ru.leonov.vktrainingclient.mvp.view.list.IPhotosItemView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class PhotosPresenter(private val mainThreadScheduler: Scheduler) : MvpPresenter<PhotosView>() {

    private val albumId = "wall"
    private val photosCount = 100

    @Inject
    lateinit var photosRepo: PhotosRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userSession: UserSession

    class PhotoListPresenter : IPhotoListPresenter {
        val photos = mutableListOf<VkPhoto>()

        override fun getCount() = photos.size

        override fun bindView(view: IPhotosItemView) {
            val photo = photos[view.pos]
            view.loadPhoto(photo.photoUrl)
        }
    }

    val photoListPresenter = PhotoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadPhotoList()
    }

    private fun loadPhotoList() {
        viewState.clearError()

        photosRepo.getPhotoList(userSession.userId, albumId, photosCount, userSession.token)
            .observeOn(mainThreadScheduler)
            .subscribe ( { vkPhotoList ->
                        viewState.showState("Photos: ${vkPhotoList.count()}")
                        photoListPresenter.photos.clear()
                        photoListPresenter.photos.addAll(vkPhotoList)
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