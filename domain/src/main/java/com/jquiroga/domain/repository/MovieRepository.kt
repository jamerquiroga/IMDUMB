package com.jquiroga.domain.repository

import com.jquiroga.domain.model.MovieCategory
import com.jquiroga.domain.model.MovieDetail
import io.reactivex.Single

interface MovieRepository {
    fun getAllCategories(): Single<List<MovieCategory>>

    fun getMovieDetail(movieId: Int): Single<MovieDetail>
}