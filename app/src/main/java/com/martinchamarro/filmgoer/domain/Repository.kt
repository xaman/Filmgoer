package com.martinchamarro.filmgoer.domain

interface Repository<ITEM> {

    fun getAll(): List<ITEM>

    fun put(item: ITEM)

    fun putAll(items: List<ITEM>)

    fun clear()

}