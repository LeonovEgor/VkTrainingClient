package ru.leonov.vktrainingclient.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.presenter.list.IPhotoListPresenter
import ru.geekbrains.poplib.mvp.view.list.IPhotosItemView
import ru.leonov.vktrainingclient.mvp.model.entity.api.photo.PhotoList
import ru.leonov.vktrainingclient.mvp.model.repository.PhotosRepository
import ru.leonov.vktrainingclient.mvp.view.PhotosView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class PhotosPresenter(
    private val mainThreadScheduler: Scheduler,
    private val token: String,
    private val userId: Int
) : MvpPresenter<PhotosView>() {

    private val albumId = "wall"
    private val photosCount = 100

    @Inject
    lateinit var photosRepo: PhotosRepository

    @Inject
    lateinit var router: Router

    class PhotoListPresenter : IPhotoListPresenter {
        val photos = mutableListOf<PhotoList>()

        override fun getCount() = photos.size

        override fun bindView(view: IPhotosItemView) {
            val photo = photos[view.pos]
            //TODO: сделать выбор фото для загрузки
            view.loadPhoto(photo.sizes[photo.sizes.lastIndex].url)
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

        photosRepo.getPhotosList(userId, albumId, photosCount, token)
            .observeOn(mainThreadScheduler)
            .subscribe ( { responseResult ->
                if (responseResult.errorCode != 0) {
                    viewState.showError(responseResult.errorMsg)
                } else {
                    responseResult.data?.let {userListResponce->
                        viewState.showState("Photos: ${userListResponce.count}")
                        photoListPresenter.photos.clear()
                        userListResponce.items?.let { photoList->
                            photoListPresenter.photos.addAll(photoList)
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
