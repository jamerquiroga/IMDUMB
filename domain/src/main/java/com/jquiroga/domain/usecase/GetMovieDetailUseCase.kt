package com.jquiroga.domain.usecase

import com.jquiroga.domain.model.MovieDetail
import com.jquiroga.domain.repository.MovieRepository
import io.reactivex.Single


class GetMovieDetailUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int): Single<MovieDetail> =
        movieRepository.getMovieDetail(movieId)
}