package com.jquiroga.data.utils

import com.jquiroga.domain.model.Movie
import com.jquiroga.domain.model.MovieCategory
import com.jquiroga.domain.model.MovieDetail

object TestDataProvider {

    const val MOVIE_ID = 550
    const val API_ERROR_MESSAGE = "Network error"

    fun movieDetail() = MovieDetail(
        id = 1,
        title = "Title",
        overview = "Overview",
        voteAverage = 9.9,
        imagePaths = emptyList(),
        cast = emptyList()
    )

    fun movieCategories() = listOf(
        MovieCategory(
            displayName = "Popular",
            movies = listOf(
                Movie(
                    id = 1,
                    title = "Movie 1",
                    posterPath = "/path1",
                    voteAverage = 8.5
                )
            )
        ),
        MovieCategory(
            displayName = "Top Rated",
            movies = listOf(
                Movie(
                    id = 2,
                    title = "Movie 2",
                    posterPath = "/path2",
                    voteAverage = 9.0
                )
            )
        )
    )
}