package br.com.app.movie.data.model

data class Details(
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<Genre>?,
    val id: Int?,
    val originCountry: List<String>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?
)