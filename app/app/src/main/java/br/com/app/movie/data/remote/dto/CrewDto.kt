package br.com.app.movie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CrewDto(
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("profile_path") val profilePath: String?,
    val job: String?
)