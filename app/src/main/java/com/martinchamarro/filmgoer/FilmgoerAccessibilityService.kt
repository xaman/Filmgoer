package com.martinchamarro.filmgoer

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class FilmgoerAccessibilityService : AccessibilityService() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}

    override fun onInterrupt() {}
}