package br.com.app.movie.data.repository

import br.com.app.movie.data.remote.api.ApiResult
import br.com.app.movie.data.remote.api.safeCallApi
import br.com.app.movie.data.remote.response.MovieResponse
import br.com.app.movie.data.remote.service.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val service: MovieService,
    private val dispatcher: CoroutineDispatcher
): MovieRepository {

    override suspend fun getNowPlaying(): Flow<ApiResult<MovieResponse>> = safeCallApi {
        service.getNowPlaying()
    }.flowOn(dispatcher)

}