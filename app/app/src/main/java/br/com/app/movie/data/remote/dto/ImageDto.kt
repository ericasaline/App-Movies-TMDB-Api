package br.com.app.movie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("file_path") val filePath: String?
)