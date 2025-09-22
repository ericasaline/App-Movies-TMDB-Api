package br.com.app.movie.data.repository

import br.com.app.movie.data.remote.api.ApiResult
import br.com.app.movie.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getNowPlaying(): Flow<ApiResult<MovieResponse>>

    suspend fun getUpcoming(): Flow<ApiResult<MovieResponse>>

}