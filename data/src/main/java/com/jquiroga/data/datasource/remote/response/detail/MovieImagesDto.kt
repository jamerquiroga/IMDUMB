package com.jquiroga.data.datasource.remote.response.detail

import com.google.gson.annotations.SerializedName

data class MovieImagesDto(
    @SerializedName("backdrops") val backdrops: List<ImageDto>,
    @SerializedName("posters") val posters: List<ImageDto>
)
