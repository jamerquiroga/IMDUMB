package com.jquiroga.data.datasource.remote.response.detail

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("file_path") val filePath: String
)
