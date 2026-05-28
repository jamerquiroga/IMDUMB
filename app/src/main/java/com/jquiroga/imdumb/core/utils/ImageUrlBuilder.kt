package com.jquiroga.imdumb.core.utils

object ImageUrlBuilder {
    fun poster(path: String?): String? =
        path?.let { "${Constants.TMDB_IMAGE_BASE_URL}${Constants.POSTER_SIZE}$it" }
}
