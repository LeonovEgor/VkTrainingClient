package ru.leonov.vktrainingclient.di.modules


import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.image.IImageLoader
import ru.geekbrains.poplib.ui.image.GlideImageLoader
import ru.leonov.vktrainingclient.ui.App
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
//    (
//    includes = [
//        NetworkStatusModule::class,
//        DataBaseModule::class
//    ]
//)
class ImageModule {

//    @Named("dir")
//    @Provides
//    fun file(): File {
//
//        val path = (App.instance.externalCacheDir?.path
//            ?: App.instance.getExternalFilesDir(null)?.path
//            ?: App.instance.filesDir.path) + File.separator + "image_cache"
//        return File(path)
//    }

//    @Singleton
//    @Provides
//    fun imageCache(database: AppDatabase, @Named("dir") dir: File): IImageCache {
//        return ImageCache(database, dir)
//    }

//    @Singleton
//    @Provides
//    fun glideImageLoader(
//        imageCache: IImageCache,
//        networkStatus: NetworkStatus
//    ): IImageLoader<ImageView> {
//        return GlideImageLoader(imageCache, networkStatus)
//    }

    @Singleton
    @Provides
    fun glideImageLoader(): IImageLoader<ImageView> {
        return GlideImageLoader()
    }

}