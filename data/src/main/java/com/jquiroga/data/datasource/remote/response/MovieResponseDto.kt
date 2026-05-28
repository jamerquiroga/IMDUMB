package com.jquiroga.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("results") val results: List<MovieDto>
)

data class MovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val voteAverage: Double
)
