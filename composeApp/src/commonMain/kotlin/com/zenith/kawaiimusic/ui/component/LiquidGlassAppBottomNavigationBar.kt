package com.zenith.kawaiimusic.ui.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.zenith.kawaiimusic.expect.ui.PlatformBackdrop
import com.zenith.kawaiimusic.ui.icon.AutoGraph
import com.zenith.kawaiimusic.ui.icon.Home
import com.zenith.kawaiimusic.ui.icon.LibraryMusic
import com.zenith.kawaiimusic.ui.icon.Search
import com.zenith.kawaiimusic.ui.icon.Sensors
import com.zenith.kawaiimusic.ui.icon.KawaiIcons
import com.zenith.kawaiimusic.ui.navigation.destination.home.AnalyticsDestination
import com.zenith.kawaiimusic.ui.navigation.destination.home.HomeDestination
import com.zenith.kawaiimusic.ui.navigation.destination.library.LibraryDestination
import com.zenith.kawaiimusic.ui.navigation.destination.library.MixForYouDestination
import com.zenith.kawaiimusic.ui.navigation.destination.search.SearchDestination
import com.zenith.kawaiimusic.viewModel.SharedViewModel
import org.jetbrains.compose.resources.StringResource
import kawaimusic.composeapp.generated.resources.Res
import kawaimusic.composeapp.generated.resources.analytics
import kawaimusic.composeapp.generated.resources.home
import kawaimusic.composeapp.generated.resources.library
import kawaimusic.composeapp.generated.resources.mix
import kawaimusic.composeapp.generated.resources.search
import kotlin.reflect.KClass

@Composable
expect fun LiquidGlassAppBottomNavigationBar(
    startDestination: Any = HomeDestination,
    navController: NavController,
    backdrop: PlatformBackdrop,
    viewModel: SharedViewModel,
    isScrolledToTop: Boolean = false,
    showAnalyticsTab: Boolean = false,
    showMixForYouTab: Boolean = false,
    onOpenNowPlaying: () -> Unit = {},
    // False draws the same folding bar in the flat material (liquid glass turned off).
    liquidGlass: Boolean = true,
    reloadDestinationIfNeeded: (KClass<*>) -> Unit = { _ -> },
)

sealed class BottomNavScreen(
    val ordinal: Int,
    val destination: Any,
    val title: StringResource,
    val icon: @Composable () -> Unit,
) {
    data object Home : BottomNavScreen(
        ordinal = 0,
        destination = HomeDestination,
        title = Res.string.home,
        icon = {
            Icon(
                KawaiIcons.Home,
                contentDescription = null,
            )
        },
    )

    data object Search : BottomNavScreen(
        ordinal = 1,
        destination = SearchDestination,
        title = Res.string.search,
        icon = {
            Icon(
                KawaiIcons.Search,
                contentDescription = null,
            )
        },
    )

    data object Library : BottomNavScreen(
        ordinal = 2,
        destination = LibraryDestination,
        title = Res.string.library,
        icon = {
            Icon(
                imageVector = KawaiIcons.LibraryMusic,
                contentDescription = null,
            )
        },
    )

    // Only shown when local tracking is enabled.
    data object Analytics : BottomNavScreen(
        ordinal = 3,
        destination = AnalyticsDestination,
        title = Res.string.analytics,
        icon = {
            Icon(
                imageVector = KawaiIcons.AutoGraph,
                contentDescription = null,
            )
        },
    )

    // Only shown while signed in to YouTube — an anonymous session gets no mixes.
    // Labelled "Mix", not "Mix for you": the full title is the widest label in the bar and forces
    // every tab to be that wide. The screen itself still uses the full title.
    data object MixForYou : BottomNavScreen(
        ordinal = 4,
        destination = MixForYouDestination,
        title = Res.string.mix,
        icon = {
            Icon(
                imageVector = KawaiIcons.Sensors,
                contentDescription = null,
            )
        },
    )
}