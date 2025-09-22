package br.com.app.movie.data.remote.service

import br.com.app.movie.data.remote.dto.CreditsDto
import br.com.app.movie.data.remote.dto.DetailsDto
import br.com.app.movie.data.remote.dto.ImagesDto
import br.com.app.movie.data.remote.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path

const val api_key = "" //TODO: mover

interface MovieService {

    @GET("movie/now_playing?$api_key")
    suspend fun getNowPlaying(): MoviesDto

    @GET("movie/upcoming?$api_key")
    suspend fun getUpcoming(): MoviesDto

    @GET("movie/{movie_id}?$api_key")
    suspend fun getDetails(@Path("movie_id") id: Int): DetailsDto

    @GET("movie/{movie_id}/images?$api_key")
    suspend fun getImages(@Path("movie_id") id: Int): ImagesDto

    @GET("movie/{movie_id}/credits?$api_key")
    suspend fun getCredits(@Path("movie_id") id: Int): CreditsDto

}