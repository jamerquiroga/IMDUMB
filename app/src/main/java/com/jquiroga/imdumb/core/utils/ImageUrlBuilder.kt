package com.jquiroga.imdumb.core.utils

object ImageUrlBuilder {
    fun poster(path: String?): String? =
        path?.let { "${Constants.TMDB_IMAGE_BASE_URL}${Constants.POSTER_SIZE}$it" }

    fun backdrop(path: String?): String? =
        path?.let { "${Constants.TMDB_IMAGE_BASE_URL}${Constants.BACKDROP_SIZE}$it" }

    fun profile(path: String?): String? =
        path?.let { "${Constants.TMDB_IMAGE_BASE_URL}${Constants.PROFILE_SIZE}$it" }
}
