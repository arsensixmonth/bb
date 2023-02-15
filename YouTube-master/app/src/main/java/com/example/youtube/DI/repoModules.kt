package com.example.youtube.DI

import com.example.youtube.data.remote.Repository
import org.koin.dsl.module

val repoModules= module {
    single { Repository(get()) }
}