package com.jquiroga.data.datasource.remote.response.category

import com.google.gson.annotations.SerializedName

data class MovieResultDto(
    @SerializedName("results") val results: List<MovieDto>
)
