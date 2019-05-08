package com.martinchamarro.filmgoer

import android.app.Application
import android.content.Intent
import com.martinchamarro.filmgoer.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Suppress("unused")
class FilmgoerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@FilmgoerApplication)
            modules(dataModule)
        }

        startService(Intent(this, FilmgoerAccessibilityService::class.java))
    }

}