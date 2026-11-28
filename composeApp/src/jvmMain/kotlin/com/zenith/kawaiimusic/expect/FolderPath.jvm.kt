package com.zenith.kawaiimusic.expect

actual fun getDownloadFolderPath(): String = System.getProperty("user.home") + "/Downloads"