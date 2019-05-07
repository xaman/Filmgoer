package com.martinchamarro.filmgoer.data

interface Cache<ITEM> {

    fun size(): Int

    fun isEmpty(): Boolean

    fun contains(item: ITEM): Boolean

    fun getAll(): List<ITEM>

    fun put(item: ITEM)

    fun putAll(items: List<ITEM>)

    fun clear()

}