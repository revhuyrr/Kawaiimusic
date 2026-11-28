package com.zenith.kawaiimusic.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.zenith.kawaiimusic.expect.ui.DesktopMiniPlayerClearance
import com.zenith.kawaiimusic.expect.ui.VerticalScrollBar
import com.zenith.kawaiimusic.ui.component.CenterLoadingBox
import com.zenith.kawaiimusic.ui.component.EndOfPage
import com.zenith.kawaiimusic.ui.component.MoodAndGenresContentItem
import com.zenith.kawaiimusic.ui.component.NormalAppBar
import com.zenith.kawaiimusic.ui.icon.ArrowBackIosNew
import com.zenith.kawaiimusic.ui.icon.KawaiIcons
import com.zenith.kawaiimusic.ui.theme.typo
import com.zenith.kawaiimusic.viewModel.MoodViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import kawaimusic.composeapp.generated.resources.*

@Composable
fun MoodScreen(
    navController: NavController,
    viewModel: MoodViewModel = koinViewModel(),
    params: String?,
) {
    val moodData by viewModel.moodsMomentObject.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = params) {
        if (params != null) {
            viewModel.getMood(params)
        }
    }

    Column {
        NormalAppBar(
            title = {
                Text(
                    text = moodData?.header ?: "",
                    style = typo().labelMedium,
                )
            },
            leftIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        KawaiIcons.ArrowBackIosNew,
                        contentDescription = "Back",
                    )
                }
            },
        )
        AnimatedVisibility(visible = !loading) {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
            ) {
                items(moodData?.items ?: emptyList()) { item ->
                    MoodAndGenresContentItem(
                        data = item,
                        navController = navController,
                    )
                }
                item {
                    EndOfPage()
                }
            }
            VerticalScrollBar(
                scrollState = listState,
                modifier = Modifier.padding(bottom = DesktopMiniPlayerClearance),
            )
        }
        AnimatedVisibility(visible = loading) {
            CenterLoadingBox(
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}