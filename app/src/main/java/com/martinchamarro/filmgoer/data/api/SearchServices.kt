package com.martinchamarro.filmgoer.data.api

import com.martinchamarro.filmgoer.data.entity.MovieEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchServices {

    companion object {
        const val HEADER_CACHE_ONE_DAY = "Cache-Control: public, max-age=86400"
    }

    @Headers(HEADER_CACHE_ONE_DAY)
    @GET("/")
    fun searchMovieByTitle(
            @Query("t") title: String
    ): Observable<MovieEntity>

}