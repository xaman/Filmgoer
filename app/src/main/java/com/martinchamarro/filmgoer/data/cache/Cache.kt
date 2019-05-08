package com.martinchamarro.filmgoer.data.cache

interface Cache<KEY, VALUE> {

    fun size(): Int

    fun isEmpty(): Boolean

    fun containsKey(key: KEY): Boolean

    fun containsValue(value: VALUE): Boolean

    fun get(key: KEY): VALUE?

    fun getAll(): List<VALUE>

    fun put(key: KEY, value: VALUE)

    fun clear()

}