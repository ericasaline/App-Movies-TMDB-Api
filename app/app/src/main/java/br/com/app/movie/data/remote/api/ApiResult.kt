package br.com.app.movie.data.remote.api

sealed class ApiResult<out T> {
    object Loading: ApiResult<Nothing>()
    data class Success<out T>(val data: T): ApiResult<T>()
    data class Error(val exception: Throwable): ApiResult<Nothing>()
}