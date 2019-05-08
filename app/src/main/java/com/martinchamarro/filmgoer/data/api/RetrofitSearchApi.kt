package com.martinchamarro.filmgoer.data.api

import com.martinchamarro.filmgoer.data.entity.MovieEntity
import com.martinchamarro.filmgoer.domain.model.Title
import io.reactivex.Observable
import io.reactivex.Scheduler

class RetrofitSearchApi(
        servicesFactory: SearchServicesFactory,
        private val subscribeOn: Scheduler) : SearchApi {

    private val services = servicesFactory.create()

    override fun search(title: Title): Observable<MovieEntity>
            = services.searchMovieByTitle(title)
            .subscribeOn(subscribeOn)

}