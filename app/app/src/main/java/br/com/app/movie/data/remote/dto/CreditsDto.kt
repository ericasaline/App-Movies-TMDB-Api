package br.com.app.movie.data.remote.dto

data class CreditsDto(
    val cast: List<CastDto>,
    val crew: List<CrewDto>
)