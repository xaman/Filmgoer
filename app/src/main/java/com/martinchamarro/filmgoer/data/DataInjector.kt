package com.martinchamarro.filmgoer.data

import com.martinchamarro.filmgoer.domain.MediaRepository

object DataInjector {

    val mediaRepository: MediaRepository by lazy { MediaRepositoryImpl() }

}