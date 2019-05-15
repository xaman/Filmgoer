package com.martinchamarro.filmgoer.data

import android.util.Log
import com.martinchamarro.filmgoer.data.api.SearchApi
import com.martinchamarro.filmgoer.data.cache.Cache
import com.martinchamarro.filmgoer.data.database.MoviesDatabase
import com.martinchamarro.filmgoer.data.entity.MovieEntity
import com.martinchamarro.filmgoer.domain.MoviesRepository
import com.martinchamarro.filmgoer.domain.model.Title
import io.reactivex.Observable
import io.reactivex.Scheduler

class MoviesRepositoryImpl(
        private val api: SearchApi,
        private val cache: Cache<Title, MovieEntity>,
        private val database: MoviesDatabase,
        private val subscribeOn: Scheduler) : MoviesRepository {

    private val tag = MoviesRepositoryImpl::class.java.simpleName
    private val searched = mutableListOf<Title>()

    override fun search(titles: List<Title>) {
        Observable.fromIterable(titles)
                .filter { !searched.contains(it) }
                .map { searched.add(it); it }
                .flatMap { title ->
                    database.load(title)?.let { entity ->
                        Log.d(tag, "FROM DATABASE ($title)")
                        Observable.just(title to entity)
                    } ?: api.search(title).map { entity ->
                        Log.d(tag, "FROM API ($title)")
                        database.save(entity)
                        title to entity
                    }
                }
                .doOnNext {
                    Log.d(tag, "TO CACHE (${it.first}) -> ${it.second}")
                    cache.put(it.first, it.second)
                }
                .subscribeOn(subscribeOn)
                .subscribe()
    }

}