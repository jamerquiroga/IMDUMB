package com.jquiroga.data.repository

import com.jquiroga.domain.model.MovieCategory
import com.jquiroga.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieDataRepository @Inject constructor(): MovieRepository {

    override fun getAllCategories(): Single<List<MovieCategory>> {
        return Single.just(emptyList())
    }
}