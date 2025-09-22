package br.com.app.movie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CastDto(
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("profile_path") val profilePath: String?,
    val character: String?
)