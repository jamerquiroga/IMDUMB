package com.jquiroga.domain.usecase


import com.jquiroga.domain.model.MovieCategory
import com.jquiroga.domain.repository.MovieRepository
import io.reactivex.Single

class GetCategoriesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Single<List<MovieCategory>> =
        movieRepository.getAllCategories().map { pairs ->
            pairs.map { (categoryName, movies) ->
                MovieCategory(
                    displayName = categoryName,
                    movies = movies
                )
            }
        }
}
