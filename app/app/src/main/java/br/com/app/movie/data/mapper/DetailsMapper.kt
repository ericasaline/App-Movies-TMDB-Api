package br.com.app.movie.data.mapper

import br.com.app.movie.data.model.Details
import br.com.app.movie.data.model.Genre
import br.com.app.movie.data.remote.dto.DetailsDto

fun DetailsDto.toDetails(): Details {
    return Details(
        adult = this.adult,
        backdropPath = this.backdropPath,
        budget = this.budget,
        genres = this.genres?.map { genreDto ->
            Genre(
                id = genreDto.id,
                name = genreDto.name
            )
        },
        id = this.id,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage
    )
}