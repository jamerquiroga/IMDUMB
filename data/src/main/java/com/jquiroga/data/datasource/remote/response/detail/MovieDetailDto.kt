package com.jquiroga.data.datasource.remote.response.detail

import com.google.gson.annotations.SerializedName

data class MovieDetailDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
)