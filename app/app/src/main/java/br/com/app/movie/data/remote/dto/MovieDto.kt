package br.com.app.movie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("genre_ids") val genres: List<Int>?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Double?
)