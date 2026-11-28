package com.zenith.kawaiimusic.expect.ui

import androidx.compose.runtime.Composable

@Composable
actual fun openEqResult(audioSessionId: Int): OpenEqLauncher =
    object : OpenEqLauncher {
        override fun launch() {
        }
    }