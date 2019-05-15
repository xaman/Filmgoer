package com.martinchamarro.filmgoer.data.di

import com.martinchamarro.filmgoer.data.cache.Cache
import com.martinchamarro.filmgoer.data.cache.InMemoryCache
import com.martinchamarro.filmgoer.data.MoviesRepositoryImpl
import com.martinchamarro.filmgoer.data.api.RetrofitSearchApi
import com.martinchamarro.filmgoer.data.api.SearchApi
import com.martinchamarro.filmgoer.data.api.SearchServicesFactory
import com.martinchamarro.filmgoer.data.database.MoviesDatabase
import com.martinchamarro.filmgoer.data.entity.MovieEntity
import com.martinchamarro.filmgoer.domain.MoviesRepository
import com.martinchamarro.filmgoer.domain.model.Title
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module


val dataModule = module {

    factory<Cache<Title, MovieEntity>> { InMemoryCache() }

    single<MoviesRepository> { MoviesRepositoryImpl(get(), get(), get()) }

    single { SearchServicesFactory() }

    single<SearchApi> { RetrofitSearchApi(get(), Schedulers.io()) }

    single { MoviesDatabase(get()) }

}