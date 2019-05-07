package com.martinchamarro.filmgoer.data

import com.martinchamarro.filmgoer.domain.Media
import com.martinchamarro.filmgoer.extensions.letIfNotNullNorEmpty

class InMemoryCache : Cache<Media> {

    private val content = mutableMapOf<String, Media>()

    override fun size() = content.size

    override fun isEmpty() = content.isEmpty()

    override fun contains(item: Media): Boolean = content[item.name] != null

    override fun getAll(): List<Media> = content.values.toList()

    override fun put(item: Media) {
        item.name.letIfNotNullNorEmpty {
            content[it] = item
        }
    }

    override fun putAll(items: List<Media>) = items.forEach { put(it) }

    override fun clear() = content.clear()

    override fun toString() = "InMemoryCache(${content.keys.joinToString(separator = ", ")})"

}