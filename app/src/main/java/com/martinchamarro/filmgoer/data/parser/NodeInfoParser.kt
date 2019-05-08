package com.martinchamarro.filmgoer.data.parser

import android.view.accessibility.AccessibilityNodeInfo
import com.martinchamarro.filmgoer.domain.model.Title
import com.martinchamarro.filmgoer.extensions.safelyGetChild

private const val ID_MOVIE_BOXART = "movie_boxart"

class NodeInfoParser : Parser<AccessibilityNodeInfo?, List<Title>> {

    override fun parse(input: AccessibilityNodeInfo?): List<Title> {
        val list = mutableListOf<Title>()
        parse(input, list)
        return list
    }

    private fun parse(input: AccessibilityNodeInfo?, list: MutableList<Title>) {
        input?.run {
            // Convert the node to a media object
            val media = when {
                isCarouselItem() -> contentDescription?.run { toString() }
                else -> null
            }
            // Add the media to the list
            media?.let { list.add(it) }
            // Parse the children
            for (i in 0..childCount) parse(safelyGetChild(i), list)
        }
    }

    private fun AccessibilityNodeInfo.isCarouselItem() = viewIdResourceName?.contains(ID_MOVIE_BOXART) ?: false

}