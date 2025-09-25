package br.com.app.movie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.movie.R
import br.com.app.movie.data.model.Movie
import br.com.app.movie.data.remote.api.ApiResult
import br.com.app.movie.data.repository.MovieRepository
import br.com.app.movie.ui.model.MovieCarousel
import br.com.app.movie.ui.model.MovieSection
import br.com.app.movie.ui.model.PosterItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModel(
    private val repository: MovieRepository
): ViewModel() {
    private val _sections = MutableStateFlow(
        MovieSection.entries.associateWith {
            MutableStateFlow<ApiResult<List<Movie>>>(ApiResult.Loading)
        }
    )
    val sections: StateFlow<Map<MovieSection, StateFlow<ApiResult<List<Movie>>>>> = _sections

    val isLoading: StateFlow<Boolean> = sections
        .flatMapLatest { sectionMap ->
            if (sectionMap.isEmpty()) flowOf(true)
                else combine(sectionMap.values) { it.any { it is ApiResult.Loading } }
        }
        .stateIn(viewModelScope, WhileSubscribed(5000), true)

    fun loadHome() {
        MovieSection.entries.forEach { section ->
            val flow = when (section) {
                MovieSection.NOW_PLAYING -> repository::getNowPlaying
                MovieSection.UPCOMING -> repository::getUpcoming
                MovieSection.TOP_RATED -> repository::getTopRated
                MovieSection.POPULAR -> repository::getPopular
            }

            loadSection(section, flow)
        }
    }

    private fun loadSection(
        section: MovieSection,
        flow: suspend () -> Flow<ApiResult<List<Movie>>>
    ) {
        val currentMap = _sections.value.toMutableMap()
        val state = currentMap[section] ?: MutableStateFlow<ApiResult<List<Movie>>>(ApiResult.Loading)

        currentMap[section] = state
        _sections.value = currentMap

        viewModelScope.launch {
            flow().collectLatest { result ->

                val movies = when (result) {
                    is ApiResult.Success -> {
                        val top = if (section == MovieSection.NOW_PLAYING ||
                            section == MovieSection.UPCOMING) result.data.take(8) else result.data
                        ApiResult.Success(top)
                    }
                    else -> result
                }

                state.value = movies
            }
        }
    }

    fun mapMovieToCarousel(
        section: MovieSection,
        result: ApiResult<List<Movie>>
    ): MovieCarousel {
        val hasMore = section == MovieSection.NOW_PLAYING || section == MovieSection.UPCOMING
        val title = when (section) {
            MovieSection.NOW_PLAYING -> R.string.text_now_playing
            MovieSection.UPCOMING ->  R.string.text_coming
            MovieSection.TOP_RATED ->  R.string.text_top_rated
            MovieSection.POPULAR ->  R.string.text_popular
        }

        return when (result) {
            is ApiResult.Success -> MovieCarousel(
                title = title,
                hasMore = hasMore,
                isLoading = false,
                items = result.data.map { movie ->
                    PosterItem(posterUrl = movie.posterPath ?: "")
                }
            )
            else -> MovieCarousel(
                title = title,
                hasMore = hasMore,
                isLoading = true,
                items = List(8) {
                    PosterItem()
                }
            )
        }
    }





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

            repository.getPopular().collect {
                Log.d("WINX", "getPopular: $it\n\n")
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

            repository.getVideos(id = 1078605).collect {
                Log.d("WINX", "getVideos: $it\n\n")
            }
        }
    }




}