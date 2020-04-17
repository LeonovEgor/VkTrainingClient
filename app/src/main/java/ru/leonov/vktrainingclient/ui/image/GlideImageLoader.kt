package ru.geekbrains.poplib.ui.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.geekbrains.poplib.mvp.model.image.IImageLoader
import timber.log.Timber
import java.io.ByteArrayOutputStream

//override val cache: IImageCache, val networkStatus: NetworkStatus
class GlideImageLoader( ) :
    IImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Timber.d("loadInto: image loading from:  $url ")
//        networkStatus.isOnlineSingle()
            //.observeOn(AndroidSchedulers.mainThread())
            //.subscribe { isOnline ->
//                if (isOnline) {
                    Timber.d("Online. Loading image from net")
                    GlideApp.with(container.context)
                        .asBitmap()
                        .load(url)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                Timber.d("Failed to load image $url")
                                return true
                            }

                            override fun onResourceReady(
                                resource: Bitmap?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {

                                //region Сохранение в кэш
//                                val compressFormat =
//                                    if (url.contains(".jpg")) Bitmap.CompressFormat.JPEG else Bitmap.CompressFormat.PNG
//                                val stream = ByteArrayOutputStream()
//                                resource?.compress(compressFormat, 100, stream)
//                                val bytes = stream.use { it.toByteArray() }
//                                cache.saveImage(url, bytes).subscribe({
//                                    Timber.d("loadInto Image saved")
//                                }, {
//                                    Timber.e(it)
//                                })
                                //endregion

                                return false
                            }
                        })
                        .into(container)
//                } else {
//                    Timber.d("Offline. Loading image from cache")
//                    cache.getBytes(url)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe ({ byteArray ->
//                            GlideApp.with(container.context)
//                                .asBitmap()
//                                .load(byteArray)
//                                .into(container)
//                        }, {
//                        Timber.e(it)
//                    })
//                }
            }
    }
//}