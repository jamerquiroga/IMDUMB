package com.jquiroga.imdumb.features.detail.model

data class MovieDetailUiModel(
    val id: Int,
    val title: String,
    val overview: String,
    val rating: String,
    val imageUrls: List<String>,
    val cast: List<ActorUiModel>
)