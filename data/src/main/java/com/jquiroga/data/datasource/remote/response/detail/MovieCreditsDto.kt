package com.jquiroga.data.datasource.remote.response.detail

import com.google.gson.annotations.SerializedName

data class MovieCreditsDto(
    @SerializedName("cast") val cast: List<CastDto>
)
