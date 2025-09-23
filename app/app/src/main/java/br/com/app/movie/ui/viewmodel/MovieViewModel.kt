package br.com.app.movie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.movie.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
): ViewModel() {

    fun teste() {
        viewModelScope.launch {
            repository.getNowPlaying().collect {
                Log.d("WINX", "getNowPlaying: $it\n\n")
            }

            repository.getUpcoming().collect {
                Log.d("WINX", "getUpcoming: $it\n\n")
            }

            repository.getTopRated().collect {
                Log.d("WINX", "getTopRated: $it\n\n")
            }

            repository.getDetails(id = 1078605).collect {
                Log.d("WINX", "getDetails: $it\n\n")
            }

            repository.getImages(id = 1078605).collect {
                Log.d("WINX", "getImages: $it\n\n")
            }

            repository.getCredits(id = 1078605).collect {
                Log.d("WINX", "getCredits: $it\n\n")
            }
        }
    }
}