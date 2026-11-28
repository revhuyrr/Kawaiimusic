package com.zenith.kawaiimusic.di

import com.zenith.kawaiimusic.viewModel.AlbumViewModel
import com.zenith.kawaiimusic.utils.VersionManager
import com.zenith.kawaiimusic.viewModel.AnalyticsViewModel
import com.zenith.kawaiimusic.viewModel.ListenTogetherSettingsViewModel
import com.zenith.kawaiimusic.viewModel.ListenTogetherViewModel
import com.zenith.kawaiimusic.viewModel.ArtistViewModel
import com.zenith.kawaiimusic.viewModel.BrowseViewModel
import com.zenith.kawaiimusic.viewModel.BrowserLoginViewModel
import com.zenith.kawaiimusic.viewModel.CommentsViewModel
import com.zenith.kawaiimusic.viewModel.SoundCloudPlayerViewModel
import com.zenith.kawaiimusic.viewModel.HomeViewModel
import com.zenith.kawaiimusic.viewModel.ImportViewModel
import com.zenith.kawaiimusic.viewModel.LibraryDynamicPlaylistViewModel
import com.zenith.kawaiimusic.viewModel.LibrarySidebarViewModel
import com.zenith.kawaiimusic.viewModel.LibraryViewModel
import com.zenith.kawaiimusic.viewModel.LocalPlaylistViewModel
import com.zenith.kawaiimusic.viewModel.LibrarySyncHostViewModel
import com.zenith.kawaiimusic.viewModel.LibrarySyncPhoneViewModel
import com.zenith.kawaiimusic.viewModel.LogInViewModel
import com.zenith.kawaiimusic.viewModel.LoginSyncHostViewModel
import com.zenith.kawaiimusic.viewModel.LoginSyncSenderViewModel
import com.zenith.kawaiimusic.viewModel.MoodViewModel
import com.zenith.kawaiimusic.viewModel.MoreAlbumsViewModel
import com.zenith.kawaiimusic.viewModel.NotificationViewModel
import com.zenith.kawaiimusic.viewModel.NowPlayingBottomSheetViewModel
import com.zenith.kawaiimusic.viewModel.PlaylistViewModel
import com.zenith.kawaiimusic.viewModel.PodcastViewModel
import com.zenith.kawaiimusic.viewModel.SearchViewModel
import com.zenith.kawaiimusic.viewModel.AutoEqViewModel
import com.zenith.kawaiimusic.viewModel.SettingsViewModel
import com.zenith.kawaiimusic.viewModel.SharedViewModel
import com.zenith.kawaiimusic.viewModel.SongSelectionViewModel
import com.zenith.kawaiimusic.viewModel.TasteViewModel
import com.zenith.kawaiimusic.viewModel.WrappedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule =
    module {
        single {
            SharedViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        single {
            SearchViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            SongSelectionViewModel(
                get(),
                get(),
            )
        }
        // Only one of these resolves on a given platform: Desktop binds the host repository, Android the
        // sender. Koin resolves dependencies when a ViewModel is created, not when it is declared.
        viewModel { LoginSyncHostViewModel(get()) }
        viewModel { LoginSyncSenderViewModel(get(), get()) }
        viewModel { LibrarySyncHostViewModel(get()) }
        viewModel { LibrarySyncPhoneViewModel(get()) }
        viewModel { BrowserLoginViewModel(get()) }
        viewModel { CommentsViewModel(get(), get()) }
        viewModel { SoundCloudPlayerViewModel(get()) }
        viewModel {
            NowPlayingBottomSheetViewModel(
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LibraryViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LibraryDynamicPlaylistViewModel(
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            ImportViewModel(
                get(),
            )
        }
        viewModel {
            AlbumViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            HomeViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            AutoEqViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            SettingsViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            ArtistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            PlaylistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LogInViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            PodcastViewModel(
                get(),
            )
        }
        viewModel {
            MoreAlbumsViewModel(
                get(),
            )
        }
        viewModel {
            BrowseViewModel(
                get(),
            )
        }
        viewModel {
            LibrarySidebarViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LocalPlaylistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            NotificationViewModel(
                get(),
            )
        }
        viewModel {
            MoodViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            AnalyticsViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            WrappedViewModel(
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            TasteViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            ListenTogetherSettingsViewModel(get())
        }
        viewModel {
            ListenTogetherViewModel(
                repository = get(),
                dataStore = get(),
                bridge = get(),
            )
        }

    }
