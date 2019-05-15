package com.martinchamarro.filmgoer.data.database

import android.content.Context
import com.martinchamarro.filmgoer.data.entity.MovieEntity
import com.martinchamarro.lazystorage.LazyStorage

class MoviesDatabase(context: Context) : Database<String, MovieEntity> {

    private val lazy = LazyStorage(context)
    private val clazz = MovieEntity::class.java

    override fun load(id: String): MovieEntity? = lazy.load(id, clazz)

    override fun loadAll(): List<MovieEntity> = lazy.loadAll(clazz)

    override fun save(item: MovieEntity) = lazy.save(item)

    override fun saveAll(items: List<MovieEntity>) = items.forEach { save(it) }

    override fun delete(id: String) = lazy.delete(id, clazz)

    override fun deleteAll() = lazy.deleteAll(clazz)

}