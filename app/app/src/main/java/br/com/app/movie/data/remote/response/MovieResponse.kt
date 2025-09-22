package br.com.app.movie.data.remote.response

import br.com.app.movie.data.remote.model.MovieModel

data class MovieResponse(
    val results: List<MovieModel>
)