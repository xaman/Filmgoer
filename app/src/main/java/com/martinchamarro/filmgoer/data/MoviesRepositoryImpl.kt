package com.martinchamarro.filmgoer.data

import android.util.Log
import com.martinchamarro.filmgoer.data.api.SearchApi
import com.martinchamarro.filmgoer.data.cache.Cache
import com.martinchamarro.filmgoer.data.entity.MovieEntity
import com.martinchamarro.filmgoer.domain.MoviesRepository
import com.martinchamarro.filmgoer.domain.model.Title

class MoviesRepositoryImpl(
        private val api: SearchApi,
        private val cache: Cache<Title, MovieEntity>) : MoviesRepository {

    private val pending = mutableListOf<Title>()

    override fun search(titles: List<Title>) {
        titles
            .filter { shouldRequestMovie(it) }
            .map { pending.add(it); it }
            .forEach { title ->
                api.search(title).subscribe(
                    { entity -> onSearchSuccess(title, entity) },
                    { error -> onSearchError(title, error) })
            }
    }

    private fun onSearchSuccess(title: String, entity: MovieEntity) {
        cache.put(title, entity)
        pending.remove(title)
        Log.d(javaClass.simpleName, "${cache.size()} movies -> $entity")
    }

    private fun onSearchError(title: String, error: Throwable) {
        pending.remove(title)
        Log.e(javaClass.simpleName, error.message)
    }

    private fun shouldRequestMovie(title: String) = !pending.contains(title) && !cache.containsKey(title)

}