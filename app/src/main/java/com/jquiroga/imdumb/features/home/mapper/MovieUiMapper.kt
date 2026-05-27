package com.jquiroga.imdumb.features.home.mapper

import com.jquiroga.domain.model.Movie
import com.jquiroga.domain.model.MovieCategory
import com.jquiroga.imdumb.features.home.model.MovieCategoryUiModel
import com.jquiroga.imdumb.features.home.model.MovieUiModel
import java.util.Locale
import javax.inject.Inject

class MovieUiMapper @Inject constructor() {

    fun toMovieUiModel(movie: Movie): MovieUiModel = MovieUiModel(
        id = movie.id,
        title = movie.title,
        posterUrl = movie.posterPath,
        rating = formatRating(movie.voteAverage)
    )

    fun toCategoryUiModel(category: MovieCategory): MovieCategoryUiModel =
        MovieCategoryUiModel(
            categoryName = category.categoryName,
            movies = category.movies.map(::toMovieUiModel)
        )

    private fun formatRating(rating: Double): String =
        String.format(Locale.getDefault(), "%.1f", rating)
}
