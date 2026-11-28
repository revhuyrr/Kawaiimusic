package com.zenith.kawaiimusic.expect.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState

/** Writing into the user's own downloads folder needs no permission on desktop. */
@Composable
actual fun rememberExternalStorageWritePermission(onResult: (granted: Boolean) -> Unit): ExternalStorageWritePermissionRequester {
    val currentOnResult by rememberUpdatedState(onResult)
    return remember {
        object : ExternalStorageWritePermissionRequester {
            override fun requestIfNeeded() = currentOnResult(true)
        }
    }
}
