package com.martinchamarro.filmgoer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import io.mattcarroll.hover.overlay.OverlayPermission
import android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS


private const val REQUEST_OVERLAY_PERMISSION = 1000


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var overlayPermissionRequested = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.accessibilityServiceButton).setOnClickListener(this)
        findViewById<Button>(R.id.permissionsButton).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.accessibilityServiceButton -> enableAccessibilityService()
            R.id.permissionsButton -> grantOverlayPermission()
        }
    }

    private fun enableAccessibilityService() {
        val intent = Intent(ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }

    private fun grantOverlayPermission() {
        // On Android M and above we need to ask the user for permission to display the Hover
        // menu within the "alert window" layer.  Use OverlayPermission to check for the permission
        // and to request it.
        if (!overlayPermissionRequested && !OverlayPermission.hasRuntimePermissionToDrawOverlay(this)) {
            val myIntent = OverlayPermission.createIntentToRequestOverlayPermission(this)
            startActivityForResult(myIntent, REQUEST_OVERLAY_PERMISSION)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (REQUEST_OVERLAY_PERMISSION == requestCode) {
            overlayPermissionRequested = true
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
