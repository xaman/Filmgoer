package com.martinchamarro.filmgoer.data.cache


class InMemoryCache<KEY, VALUE> : Cache<KEY, VALUE> {

    private val content = mutableMapOf<KEY, VALUE>()

    override fun size() = content.size

    override fun isEmpty() = content.isEmpty()

    override fun containsKey(key: KEY) = content.containsKey(key)

    override fun containsValue(value: VALUE) = content.containsValue(value)

    override fun get(key: KEY): VALUE? = content[key]

    override fun getAll(): List<VALUE> = content.values.toList()

    override fun put(key: KEY, value: VALUE) {
        content[key] = value
    }

    override fun clear() = content.clear()

}