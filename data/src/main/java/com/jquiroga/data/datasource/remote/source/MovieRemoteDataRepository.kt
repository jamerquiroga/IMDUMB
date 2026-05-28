package com.jquiroga.data.datasource.remote.source

import com.jquiroga.data.datasource.remote.api.MovieApiService
import com.jquiroga.data.datasource.remote.response.category.MovieResultDto
import com.jquiroga.data.datasource.remote.response.detail.MovieCreditsDto
import com.jquiroga.data.datasource.remote.response.detail.MovieDetailDto
import com.jquiroga.data.datasource.remote.response.detail.MovieImagesDto
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {

    fun getMoviesByCategory(category: String): Single<MovieResultDto> {
        return movieApiService.getMoviesByCategory(category)
    }

    fun getMovieDetail(movieId: Int): Single<MovieDetailDto> {
        return movieApiService.getMovieDetail(movieId)
    }

    fun getMovieCredits(movieId: Int): Single<MovieCreditsDto> {
        return movieApiService.getMovieCredits(movieId)
    }

    fun getMovieImages(movieId: Int): Single<MovieImagesDto> {
        return movieApiService.getMovieImages(movieId)
    }
}