package br.com.app.movie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.movie.data.remote.api.ApiResult
import br.com.app.movie.data.remote.response.MovieResponse
import br.com.app.movie.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
): ViewModel() {


    private val _movies = MutableStateFlow<ApiResult<MovieResponse>>(ApiResult.Loading)
    val movies = _movies


    fun teste() {
        viewModelScope.launch {
            repository.getNowPlaying().collect {

                Log.d("WINX", "getNowPlaying: $it")
            }
        }
    }


}