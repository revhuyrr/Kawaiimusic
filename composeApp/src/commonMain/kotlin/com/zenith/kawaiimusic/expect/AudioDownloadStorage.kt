package com.zenith.kawaiimusic.expect

/** Returns an app-private directory where the downloader can write its temporary files. */
expect fun getAudioDownloadTempDirectory(): String

/** Publishes a completed temporary MP3 to the user's Download/Kawai Mp3 folder. */
expect suspend fun saveDownloadedAudioFile(tempFilePath: String, fileName: String): Boolean
