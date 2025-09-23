package br.com.app.movie.data.mapper

import br.com.app.movie.data.model.Movie
import br.com.app.movie.data.remote.dto.MovieDto

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}