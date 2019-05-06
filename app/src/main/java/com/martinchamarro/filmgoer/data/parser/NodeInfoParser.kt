package com.martinchamarro.filmgoer.data.parser

import android.view.accessibility.AccessibilityNodeInfo
import com.martinchamarro.filmgoer.domain.Media
import com.martinchamarro.filmgoer.extensions.safelyGetChild

private const val ID_MOVIE_BOXART = "movie_boxart"

class NodeInfoParser : Parser<AccessibilityNodeInfo?, List<Media>> {

    override fun parse(node: AccessibilityNodeInfo?): List<Media> {
        val list = mutableListOf<Media>()
        parse(node, list)
        return list
    }

    private fun parse(node: AccessibilityNodeInfo?, list: MutableList<Media>) {
        node?.run {
            // Convert the node to a media object
            val media = when {
                isCarouselItem() -> contentDescription?.run { Media(toString()) }
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