package com.martinchamarro.filmgoer.extensions

import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo


private val TAG = AccessibilityNodeInfo::class.java.simpleName

fun AccessibilityNodeInfo.tree() = tree(0)

private fun AccessibilityNodeInfo.tree(level: Int) {
    var output = EMPTY
    for (i in 0..level) output += "- "
    output += stringValue
    Log.d(TAG, output)
    Log.v(TAG, toString())
    for (j in 0..childCount) {
        try {
            getChild(j).tree(level + 1)
        } catch (e: Exception) {}
    }
}

val AccessibilityNodeInfo.stringValue: String
    get() {
        var output = className.toString()
        viewIdResourceName.letIfNotNullNorEmpty { output += "($it)" }
        text.letIfNotNullNorEmpty { output += ": $it" }
        contentDescription.letIfNotNullNorEmpty { output += " ($it)" }
        return output
    }