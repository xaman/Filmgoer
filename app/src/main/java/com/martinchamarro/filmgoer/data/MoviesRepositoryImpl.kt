package com.martinchamarro.filmgoer.data

import com.martinchamarro.filmgoer.data.api.SearchApi
import com.martinchamarro.filmgoer.data.cache.Cache
import com.martinchamarro.filmgoer.data.entity.MovieEntity
import com.martinchamarro.filmgoer.domain.MoviesRepository
import com.martinchamarro.filmgoer.domain.model.Title

class MoviesRepositoryImpl(
        private val api: SearchApi,
        private val cache: Cache<Title, MovieEntity>) : MoviesRepository {

    override fun search(titles: List<Title>) {
        titles
            .filter { !cache.containsKey(it) }
            .forEach { title ->
                api.search(title).subscribe { entity -> cache.put(title, entity) }
            }
    }

}