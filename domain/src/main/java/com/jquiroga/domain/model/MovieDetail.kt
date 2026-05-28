package com.jquiroga.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val imagePaths: List<String>,
    val cast: List<Actor>
)