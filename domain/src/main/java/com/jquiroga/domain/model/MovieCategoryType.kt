package com.jquiroga.domain.model

enum class MovieCategoryType(val categoryName: String, val displayName: String) {
    POPULAR(categoryName = "popular", displayName = "Populares"),
    TOP_RATED(categoryName = "top_rated", displayName = "Mejores valorados"),
    UPCOMING(categoryName = "upcoming", displayName = "Próximos estrenos"),
    NOW_PLAYING(categoryName = "now_playing", displayName = "En cartelera")
}
