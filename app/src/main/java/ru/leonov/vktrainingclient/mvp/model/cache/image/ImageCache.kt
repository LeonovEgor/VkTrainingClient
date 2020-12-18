package ru.leonov.vktrainingclient.mvp.model.cache.image

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.leonov.vktrainingclient.mvp.model.cache.IImageCache
import ru.leonov.vktrainingclient.mvp.model.entity.room.db.AppDatabase
import ru.leonov.vktrainingclient.mvp.model.entity.room.RoomCachedImage
import ru.leonov.vktrainingclient.mvp.utils.toMD5
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageCache(private val database: AppDatabase, private val dir: File) :
    IImageCache {
    private val jpg = ".jpg"
    private val png = ".png"

    override fun contains(url: String): @NonNull Single<Boolean> =
        Single.fromCallable {
            database.imageDao.findByUrl(url) != null
        }.subscribeOn(Schedulers.io())

    override fun getBytes(url: String): Maybe<ByteArray?> = Maybe.fromCallable {
        database.imageDao.findByUrl(url)?.let {
            File(it.localPath).inputStream().readBytes()
        }
    }.subscribeOn(Schedulers.io())

    override fun saveImage(url: String, bytes: ByteArray): @NonNull Completable =
        Completable.create { emitter ->
            if (!dir.exists() && !dir.mkdirs()) {
                emitter.onError(IOException("Failed to create cache! Dir: ${dir.absolutePath}"))
                return@create
            }

            val fileFormat = if (url.contains(jpg)) jpg else png
            val imageFile = File(dir, url.toMD5() + fileFormat)
            Timber.d("$dir ${url.toMD5()} + $fileFormat")
            try {
                FileOutputStream(imageFile).use { stream ->
                    stream.write(bytes)
                }
            } catch (e: Exception) {
                emitter.onError(e)
            }
            Timber.d("saveImage image saved. Try to add image info to database")
            database.imageDao.insert(RoomCachedImage(url,imageFile.path)
            )
            Timber.d("saveImage image info added to database")
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())

}