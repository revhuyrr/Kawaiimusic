package com.zenith.kawaiimusic.expect

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

actual fun getAudioDownloadTempDirectory(): String =
    File(System.getProperty("java.io.tmpdir"), "kawai_music_audio_exports")
        .apply { mkdirs() }
        .absolutePath

actual suspend fun saveDownloadedAudioFile(tempFilePath: String, fileName: String): Boolean =
    withContext(Dispatchers.IO) {
        runCatching {
            val source = File(tempFilePath)
            if (!source.isFile) return@runCatching false
            val destinationDirectory = File(getDownloadFolderPath(), "Kawai Mp3")
            if (!destinationDirectory.exists() && !destinationDirectory.mkdirs()) return@runCatching false
            source.copyTo(File(destinationDirectory, File(fileName).name), overwrite = true)
            source.delete()
            true
        }.getOrDefault(false)
    }
