package com.jquiroga.domain.repository

import com.jquiroga.domain.model.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getAllCategories(): Single<List<Pair<String, List<Movie>>>>
}