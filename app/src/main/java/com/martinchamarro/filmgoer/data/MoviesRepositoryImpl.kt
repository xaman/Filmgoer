package com.martinchamarro.filmgoer.data

import android.util.Log
import com.martinchamarro.filmgoer.data.api.SearchApi
import com.martinchamarro.filmgoer.data.cache.Cache
import com.martinchamarro.filmgoer.data.database.MoviesDatabase
import com.martinchamarro.filmgoer.data.entity.MovieEntity
import com.martinchamarro.filmgoer.domain.MoviesRepository
import com.martinchamarro.filmgoer.domain.model.Title

class MoviesRepositoryImpl(
        private val api: SearchApi,
        private val cache: Cache<Title, MovieEntity>,
        private val database: MoviesDatabase) : MoviesRepository {

    private val tag = MoviesRepositoryImpl::class.java.simpleName
    private val searched = mutableListOf<Title>()

    override fun search(titles: List<Title>) {
        titles
            .filter { shouldRequestMovie(it) }
            .map { searched.add(it); it }
            .forEach { title ->
                api.search(title).subscribe(
                    { entity -> onSearchSuccess(title, entity) },
                    { error -> onSearchError(title, error) })
            }
    }

    private fun onSearchSuccess(title: String, entity: MovieEntity) {
        cache.put(title, entity)
        database.save(entity)
        Log.d(tag, "NEW MOVIE: ($title) -> $entity")
    }

    private fun onSearchError(title: String, error: Throwable) {
        Log.e(tag, "ERROR: ($title) -> ${error.message}")
    }

    private fun shouldRequestMovie(title: String): Boolean {

        if (searched.contains(title)) {
            Log.d(tag, "SEARCHED: ($title)")
            return false
        }

        if (database.load(title) != null) {
            Log.d(tag, "DATABASE: ($title)")
            return false
        }

        return true
    }
}