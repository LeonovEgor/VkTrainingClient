package ru.leonov.vktrainingclient.di.modules


import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.leonov.vktrainingclient.mvp.model.cache.IImageCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase
import ru.leonov.vktrainingclient.mvp.model.image.IImageLoader
import ru.leonov.vktrainingclient.ui.App
import ru.leonov.vktrainingclient.ui.image.GlideImageLoader
import ru.leonov.vktrainingclient.mvp.model.cache.image.ImageCache
import ru.leonov.vktrainingclient.ui.network.NetworkStatus
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
    (
    includes = [
        NetworkStatusModule::class,
        DataBaseModule::class
    ]
)
class ImageModule {

    @Named("dir")
    @Provides
    fun file(): File {

        val path = (App.instance.externalCacheDir?.path
            ?: App.instance.getExternalFilesDir(null)?.path
            ?: App.instance.filesDir.path) + File.separator + "image_cache"
        return File(path)
    }

    @Singleton
    @Provides
    fun imageCache(database: AppDatabase, @Named("dir") dir: File): IImageCache {
        return ImageCache(
            database,
            dir
        )
    }

    @Singleton
    @Provides
    fun glideImageLoader(
        imageCache: IImageCache,
        networkStatus: NetworkStatus
    ): IImageLoader<ImageView> {
        return GlideImageLoader(imageCache, networkStatus)
    }

}