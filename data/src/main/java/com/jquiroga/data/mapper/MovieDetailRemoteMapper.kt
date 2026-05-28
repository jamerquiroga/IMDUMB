package com.jquiroga.data.mapper

import com.jquiroga.data.datasource.remote.response.detail.CastDto
import com.jquiroga.data.datasource.remote.response.detail.MovieCreditsDto
import com.jquiroga.data.datasource.remote.response.detail.MovieDetailDto
import com.jquiroga.data.datasource.remote.response.detail.MovieImagesDto
import com.jquiroga.domain.model.Actor
import com.jquiroga.domain.model.MovieDetail
import javax.inject.Inject

class MovieDetailRemoteMapper @Inject constructor() {

    fun toDomain(
        detail: MovieDetailDto,
        credits: MovieCreditsDto,
        images: MovieImagesDto
    ): MovieDetail {
        val imagePaths = buildImagePaths(detail, images)
        return MovieDetail(
            id = detail.id,
            title = detail.title,
            overview = detail.overview,
            voteAverage = detail.voteAverage,
            imagePaths = imagePaths,
            cast = credits.cast.take(15).map(::toActor)
        )
    }

    private fun buildImagePaths(detail: MovieDetailDto, images: MovieImagesDto): List<String> {
        val paths = linkedSetOf<String>()
        images.backdrops.take(8).forEach { paths.add(it.filePath) }
        if (paths.isEmpty()) {
            images.posters.take(5).forEach { paths.add(it.filePath) }
        }
        return paths.toList()
    }

    private fun toActor(dto: CastDto): Actor = Actor(
        id = dto.id,
        name = dto.name,
        character = dto.character,
        profilePath = dto.profilePath
    )
}