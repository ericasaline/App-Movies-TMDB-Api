package br.com.app.movie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.movie.data.remote.api.ApiResult
import br.com.app.movie.data.remote.dto.MoviesDto
import br.com.app.movie.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
): ViewModel() {


    private val _movies = MutableStateFlow<ApiResult<MoviesDto>>(ApiResult.Loading)
    val movies = _movies


    fun teste() {
        viewModelScope.launch {
            repository.getNowPlaying().collect {
                Log.d("WINX", "getNowPlaying: $it\n\n")
            }

            repository.getUpcoming().collect {
                Log.d("WINX", "getUpcoming: $it\n\n")
            }

            repository.getDetails(id = 1078605).collect {
                Log.d("WINX", "getDetails: $it\n\n")
            }
        }
    }


}