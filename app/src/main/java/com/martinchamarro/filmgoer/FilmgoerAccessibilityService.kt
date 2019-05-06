package com.martinchamarro.filmgoer

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo.*
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.martinchamarro.filmgoer.extensions.tree
import com.martinchamarro.filmgoer.extensions.typeValue

class FilmgoerAccessibilityService : AccessibilityService() {

    companion object {
        private val TAG = FilmgoerAccessibilityService::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(javaClass.simpleName, "Accessibility service created")
    }

    override fun onServiceConnected() {
        val info = serviceInfo

        // Set the type of events that this service wants to listen to.  Others
        // won't be passed to this service.
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK

        // If you only want this service to work with specific applications, set their
        // package names here.  Otherwise, when the service is activated, it will listen
        // to events from all applications.
        info.packageNames = arrayOf(Config.NETFLIX_PACKAGE)

        // Set the type of feedback your service will provide.
        info.feedbackType = FEEDBACK_ALL_MASK

        // Default services are invoked only if no package-specific ones are present
        // for the type of AccessibilityEvent generated.  This service *is*
        // application-specific, so the flag isn't necessary.  If this was a
        // general-purpose service, it would be worth considering setting the
        // DEFAULT flag.

        info.flags = DEFAULT or FLAG_REPORT_VIEW_IDS or FLAG_INCLUDE_NOT_IMPORTANT_VIEWS
        info.notificationTimeout = 100
        this.serviceInfo = info
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.run {
            Log.d(TAG, "Accessibility event: $typeValue")
            Log.v(TAG, toString())
            source?.tree()
        }
    }

    override fun onInterrupt() {}
}