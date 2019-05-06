package com.martinchamarro.filmgoer.extensions

import android.view.accessibility.AccessibilityNodeInfo


fun AccessibilityNodeInfo.printTree() = printTree(0)

private fun AccessibilityNodeInfo.printTree(level: Int) {
    var output = EMPTY
    for (i in 0..level) output += "- "
    output += stringValue
    println(output)
    println(toString())
    for (j in 0..childCount) {
        safelyGetChild(j)?.printTree(level + 1)
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

fun AccessibilityNodeInfo.safelyGetChild(pos: Int): AccessibilityNodeInfo? =
    try {
        getChild(pos)
    } catch (e: Exception) {
        null
    }