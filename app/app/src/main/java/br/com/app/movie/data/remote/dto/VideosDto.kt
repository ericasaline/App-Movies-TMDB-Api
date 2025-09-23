package br.com.app.movie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VideosDto(
    @SerializedName("results") val videos: List<VideoDto>
)