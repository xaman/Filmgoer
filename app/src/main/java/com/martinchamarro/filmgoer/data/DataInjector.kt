package com.martinchamarro.filmgoer.data

import com.martinchamarro.filmgoer.domain.Media

object DataInjector {

    val cache: Cache<Media> by lazy { InMemoryCache() }

}