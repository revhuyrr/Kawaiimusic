package com.zenith.kawaiimusic.expect

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.maxrave.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.mp.KoinPlatform.getKoin
import java.io.File

private const val TAG = "AudioDownloadStorage"
private const val MP3_FOLDER = "Kawai Mp3"

actual fun getAudioDownloadTempDirectory(): String {
    val activity: AppCompatActivity = getKoin().get()
    return File(activity.cacheDir, "audio_exports").apply { mkdirs() }.absolutePath
}

actual suspend fun saveDownloadedAudioFile(tempFilePath: String, fileName: String): Boolean =
    withContext(Dispatchers.IO) {
        val source = File(tempFilePath)
        if (!source.isFile) {
            Logger.e(TAG, "Completed MP3 is missing: $tempFilePath")
            return@withContext false
        }

        val activity: AppCompatActivity = getKoin().get()
        val resolver = activity.contentResolver
        var insertedUri: android.net.Uri? = null
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val values =
                    ContentValues().apply {
                        put(MediaStore.Downloads.DISPLAY_NAME, File(fileName).name)
                        put(MediaStore.Downloads.MIME_TYPE, "audio/mpeg")
                        put(MediaStore.Downloads.RELATIVE_PATH, "${Environment.DIRECTORY_DOWNLOADS}/$MP3_FOLDER")
                        put(MediaStore.Downloads.IS_PENDING, 1)
                    }
                val uri =
                    resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values)
                        ?: error("MediaStore did not create a Downloads entry")
                insertedUri = uri
                val output = resolver.openOutputStream(uri, "w") ?: error("Could not open the Downloads entry")
                source.inputStream().use { input -> output.use { input.copyTo(it) } }
                val completed = ContentValues().apply { put(MediaStore.Downloads.IS_PENDING, 0) }
                resolver.update(uri, completed, null, null)
            } else {
                val granted =
                    ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED
                if (!granted) error("Storage permission was not granted")
                val destinationDirectory =
                    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), MP3_FOLDER)
                if (!destinationDirectory.exists() && !destinationDirectory.mkdirs()) {
                    error("Could not create ${destinationDirectory.absolutePath}")
                }
                source.copyTo(File(destinationDirectory, File(fileName).name), overwrite = true)
            }
            source.delete()
            true
        } catch (error: Exception) {
            insertedUri?.let { runCatching { resolver.delete(it, null, null) } }
            Logger.e(TAG, "Could not save ${File(fileName).name} to Download/$MP3_FOLDER: ${error.message}")
            false
        }
    }
