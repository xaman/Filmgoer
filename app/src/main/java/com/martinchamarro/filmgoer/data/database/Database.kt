package com.martinchamarro.filmgoer.data.database

interface Database<ID, ITEM> {

    fun load(id: ID): ITEM?

    fun loadAll(): List<ITEM>

    fun save(item: ITEM)

    fun saveAll(items: List<ITEM>)

    fun delete(id: ID)

    fun deleteAll()

}