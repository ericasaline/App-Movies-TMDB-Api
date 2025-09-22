package br.com.app.movie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImagesDto(
    @SerializedName("backdrops") val images: List<ImageDto>
)