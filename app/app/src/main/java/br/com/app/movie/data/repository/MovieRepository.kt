package br.com.app.movie.data.repository

import br.com.app.movie.data.model.Credits
import br.com.app.movie.data.model.Details
import br.com.app.movie.data.model.Image
import br.com.app.movie.data.model.Movie
import br.com.app.movie.data.model.Video
import br.com.app.movie.data.remote.api.ApiResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getNowPlaying(): Flow<ApiResult<List<Movie>>>

    suspend fun getUpcoming(): Flow<ApiResult<List<Movie>>>

    suspend fun getTopRated(): Flow<ApiResult<List<Movie>>>

    suspend fun getPopular(): Flow<ApiResult<List<Movie>>>

    suspend fun getDetails(id: Int): Flow<ApiResult<Details>>

    suspend fun getImages(id: Int): Flow<ApiResult<List<Image>>>

    suspend fun getCredits(id: Int): Flow<ApiResult<Credits>>

    suspend fun getVideos(id: Int): Flow<ApiResult<List<Video>>>

}