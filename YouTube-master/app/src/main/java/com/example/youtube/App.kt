package com.example.youtube

import android.app.Application
import androidx.room.Room
import com.example.youtube.DI.repoModules
import com.example.youtube.DI.viewModules
import com.example.youtube.data.local.AppADataBase
import com.example.youtube.data.remote.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(applicationContext, AppADataBase::class.java, "playlist.db")
            .build()

        startKoin {
            androidContext(this@App)
            modules(arrayListOf(repoModules, viewModules, networkModule))
        }

    }

    companion object {
        lateinit var db: AppADataBase
    }
}