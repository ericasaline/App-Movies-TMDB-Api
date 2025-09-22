package br.com.app.movie.data.remote.service

import br.com.app.movie.data.remote.response.MovieResponse
import retrofit2.http.GET

const val api_key = "" //TODO: mover

interface MovieService {

    @GET("movie/now_playing?$api_key")
    suspend fun getNowPlaying(): MovieResponse

}