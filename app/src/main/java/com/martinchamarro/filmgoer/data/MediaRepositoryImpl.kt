package com.martinchamarro.filmgoer.data

import com.martinchamarro.filmgoer.domain.Media
import com.martinchamarro.filmgoer.domain.MediaRepository
import com.martinchamarro.filmgoer.extensions.letIfNotNullNorEmpty

class MediaRepositoryImpl : MediaRepository {

    private val content = mutableMapOf<String, Media>()

    override fun getAll(): List<Media> = content.values.toList()

    override fun put(item: Media) {
        item.name.letIfNotNullNorEmpty {
            content[it] = item
        }
    }

    override fun putAll(items: List<Media>) = items.forEach { put(it) }

    override fun clear() = content.clear()

    override fun toString() = "MediaRepositoryImpl(${content.keys.joinToString(separator = ", ")})"

}