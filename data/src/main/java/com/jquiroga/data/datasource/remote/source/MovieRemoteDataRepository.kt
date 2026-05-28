package com.jquiroga.data.datasource.remote.source

import com.jquiroga.data.datasource.remote.api.MovieApiService
import com.jquiroga.data.datasource.remote.response.MovieResponseDto
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {

    fun getMoviesByCategory(category: String): Single<MovieResponseDto> {
        return movieApiService.getMoviesByCategory(category)
    }

}