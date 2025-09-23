package br.com.app.movie.data.repository

import br.com.app.movie.data.mapper.toCredits
import br.com.app.movie.data.mapper.toDetails
import br.com.app.movie.data.mapper.toImage
import br.com.app.movie.data.mapper.toMovie
import br.com.app.movie.data.model.Credits
import br.com.app.movie.data.model.Details
import br.com.app.movie.data.model.Image
import br.com.app.movie.data.model.Movie
import br.com.app.movie.data.remote.api.ApiResult
import br.com.app.movie.data.remote.api.safeCallApi
import br.com.app.movie.data.remote.service.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val service: MovieService,
    private val dispatcher: CoroutineDispatcher
): MovieRepository {

    override suspend fun getNowPlaying(): Flow<ApiResult<List<Movie>>> = safeCallApi {
        service.getNowPlaying().results.map { it.toMovie() }
    }.flowOn(dispatcher)

    override suspend fun getUpcoming(): Flow<ApiResult<List<Movie>>> = safeCallApi {
        service.getUpcoming().results.map { it.toMovie() }
    }.flowOn(dispatcher)

    override suspend fun getTopRated(): Flow<ApiResult<List<Movie>>> = safeCallApi {
        service.getTopRated().results.map { it.toMovie() }
    }.flowOn(dispatcher)

    override suspend fun getPopular(): Flow<ApiResult<List<Movie>>> = safeCallApi {
        service.getPopular().results.map { it.toMovie() }
    }.flowOn(dispatcher)

    override suspend fun getDetails(id: Int): Flow<ApiResult<Details>> = safeCallApi {
        service.getDetails(id).toDetails()
    }.flowOn(dispatcher)

    override suspend fun getImages(id: Int): Flow<ApiResult<List<Image>>> = safeCallApi {
        service.getImages(id).images.map { it.toImage() }
    }.flowOn(dispatcher)

    override suspend fun getCredits(id: Int): Flow<ApiResult<Credits>> = safeCallApi {
        service.getCredits(id).toCredits()
    }.flowOn(dispatcher)

}