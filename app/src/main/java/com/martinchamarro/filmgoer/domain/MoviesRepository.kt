package com.martinchamarro.filmgoer.domain

import com.martinchamarro.filmgoer.domain.model.Title

interface MoviesRepository {

    fun search(titles: List<Title>)

}