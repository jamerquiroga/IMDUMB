package com.jquiroga.imdumb.features.detail.mapper

import com.jquiroga.domain.model.Actor
import com.jquiroga.domain.model.MovieDetail
import com.jquiroga.imdumb.core.utils.ImageUrlBuilder
import com.jquiroga.imdumb.features.detail.model.ActorUiModel
import com.jquiroga.imdumb.features.detail.model.MovieDetailUiModel
import java.util.Locale
import javax.inject.Inject

class MovieDetailUiMapper @Inject constructor() {
    fun toDetailUiModel(detail: MovieDetail): MovieDetailUiModel = MovieDetailUiModel(
        id = detail.id,
        title = detail.title,
        overview = detail.overview,
        rating = formatRating(detail.voteAverage),
        imageUrls = detail.imagePaths.mapNotNull { ImageUrlBuilder.backdrop(it) },
        cast = detail.cast.map(::toActorUiModel)
    )

    private fun toActorUiModel(actor: Actor): ActorUiModel = ActorUiModel(
        id = actor.id,
        name = actor.name,
        character = actor.character,
        profileUrl = ImageUrlBuilder.profile(actor.profilePath)
    )

    private fun formatRating(rating: Double): String =
        String.format(Locale.getDefault(), "%.1f", rating)
}