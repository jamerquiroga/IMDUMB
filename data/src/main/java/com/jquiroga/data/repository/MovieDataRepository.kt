package com.jquiroga.data.repository

import com.jquiroga.domain.model.Movie
import com.jquiroga.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieDataRepository @Inject constructor(): MovieRepository {

    override fun getAllCategories(): Single<List<Pair<String, List<Movie>>>> {
        TODO("Not yet implemented")
    }
}