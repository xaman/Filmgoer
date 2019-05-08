package com.martinchamarro.filmgoer.data.api

import com.martinchamarro.filmgoer.data.entity.MovieEntity
import com.martinchamarro.filmgoer.domain.model.Title
import io.reactivex.Observable

interface SearchApi {
    fun search(title: Title): Observable<MovieEntity>
}