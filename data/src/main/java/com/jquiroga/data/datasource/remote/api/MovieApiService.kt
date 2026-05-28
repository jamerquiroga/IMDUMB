package com.jquiroga.data.datasource.remote.api

import com.jquiroga.data.datasource.remote.response.MovieResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/{category}")
    fun getMoviesByCategory(
        @Path("category") category: String
    ): Single<MovieResponseDto>

}