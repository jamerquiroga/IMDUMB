package com.jquiroga.data.mapper

import com.jquiroga.data.datasource.remote.response.category.MovieDto
import com.jquiroga.domain.model.Movie
import javax.inject.Inject

class MovieRemoteMapper @Inject constructor() {
    fun toDomain(dto: MovieDto): Movie = Movie(
        id = dto.id,
        title = dto.title,
        posterPath = dto.posterPath,
        voteAverage = dto.voteAverage
    )
}