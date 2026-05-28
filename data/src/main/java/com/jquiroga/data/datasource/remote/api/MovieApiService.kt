package com.jquiroga.data.datasource.remote.api

import com.jquiroga.data.datasource.remote.response.category.MovieResultDto
import com.jquiroga.data.datasource.remote.response.detail.MovieCreditsDto
import com.jquiroga.data.datasource.remote.response.detail.MovieDetailDto
import com.jquiroga.data.datasource.remote.response.detail.MovieImagesDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/{category}")
    fun getMoviesByCategory(
        @Path("category") category: String
    ): Single<MovieResultDto>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Single<MovieDetailDto>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movieId: Int): Single<MovieCreditsDto>

    @GET("movie/{movie_id}/images")
    fun getMovieImages(@Path("movie_id") movieId: Int): Single<MovieImagesDto>

}