package com.example.youtube.DI

import com.example.youtube.playlist.PlayListViewModel
import com.example.youtube.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlayListViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}