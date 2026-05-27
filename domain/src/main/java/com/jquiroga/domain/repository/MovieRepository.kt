package com.jquiroga.domain.repository

import com.jquiroga.domain.model.Movie
import com.jquiroga.domain.model.MovieCategory
import io.reactivex.Single

interface MovieRepository {
    fun getAllCategories(): Single<List<MovieCategory>>
}