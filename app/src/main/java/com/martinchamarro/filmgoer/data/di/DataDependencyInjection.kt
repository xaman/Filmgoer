package com.martinchamarro.filmgoer.data.di

import com.martinchamarro.filmgoer.data.Cache
import com.martinchamarro.filmgoer.data.InMemoryCache
import com.martinchamarro.filmgoer.domain.Media
import org.koin.dsl.module


val dataModule = module {

    single<Cache<Media>> { InMemoryCache() }

}