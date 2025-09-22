package br.com.app.movie.data.remote.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> safeCallApi(block: suspend () -> T): Flow<ApiResult<T>> = flow {
    emit(ApiResult.Loading)
    try {
        val result = block()
        emit(ApiResult.Success(data = result))
    } catch (exception: Exception) {
        emit(ApiResult.Error(exception = exception))
    }
}