package com.martinchamarro.filmgoer

import android.app.Application
import android.content.Intent

class FilmgoerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startService(Intent(this, FilmgoerAccessibilityService::class.java))
    }

}