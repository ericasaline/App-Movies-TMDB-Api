package br.com.app.movie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.movie.data.model.Movie
import br.com.app.movie.data.remote.api.ApiResult
import br.com.app.movie.data.repository.MovieRepository
import br.com.app.movie.ui.model.MovieCarousel
import br.com.app.movie.ui.model.MovieItem
import br.com.app.movie.ui.model.MovieSection
import br.com.app.movie.ui.model.SortOption
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
): ViewModel() {

    private val _nowPlayingState = MutableStateFlow<ApiResult<List<Movie>>>(ApiResult.Loading)
    val nowPlayingState = _nowPlayingState.asStateFlow()

    private val _upcomingState = MutableStateFlow<ApiResult<List<Movie>>>(ApiResult.Loading)
    val upcomingState = _upcomingState.asStateFlow()

    private val _sections = MutableStateFlow(
        MovieSection.entries.associateWith {
            MutableStateFlow<ApiResult<List<Movie>>>(ApiResult.Loading)
        }
    )
    val sections: StateFlow<Map<MovieSection, StateFlow<ApiResult<List<Movie>>>>> = _sections

    @OptIn(ExperimentalCoroutinesApi::class)
    val isLoading: StateFlow<Boolean> = sections
        .flatMapLatest { sectionMap ->
            if (sectionMap.isEmpty()) flowOf(true)
                else combine(sectionMap.values) { it.any { it is ApiResult.Loading } }
        }
        .stateIn(viewModelScope, WhileSubscribed(5000), true)

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

    fun mapMovieToCarousel(
        section: MovieSection,
        result: ApiResult<List<Movie>>
    ): MovieCarousel {
        val hasMore = section == MovieSection.NOW_PLAYING || section == MovieSection.UPCOMING

        return when (result) {
            is ApiResult.Success -> MovieCarousel(
                title = section.titleRes,
                hasMore = hasMore,
                isLoading = false,
                items = result.data.map { movie ->
                    MovieItem(
                        id = movie.id ?: 0,
                        posterUrl = movie.posterPath.orEmpty()
                    )
                }
            )
            else -> MovieCarousel(
                title = section.titleRes,
                hasMore = hasMore,
                isLoading = true,
                items = List(3) {
                    MovieItem()
                }
            )
        }
    }

    fun mapMovieToGrid(
        movies: List<Movie>,
        sortOption: SortOption
    ): List<MovieItem> {
        val sorted = when (sortOption) {
            SortOption.NAME -> movies.sortedBy { it.title }
            SortOption.DATE -> movies.sortedBy { it.releaseDate }
            SortOption.RATING -> movies.sortedByDescending { it.voteAverage }
            SortOption.OTHER -> movies
        }

        return sorted.map { movie ->
            MovieItem(
                id = movie.id ?: 0,
                title = movie.title.orEmpty(),
                posterUrl = movie.posterPath.orEmpty()
            )
        }
    }

    fun getNowPlayingAndUpcoming() {
        _nowPlayingState.value =_sections.value[MovieSection.NOW_PLAYING]?.value
            ?: ApiResult.Error(Exception())

        _upcomingState.value =_sections.value[MovieSection.UPCOMING]?.value
            ?: ApiResult.Error(Exception())
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
                    is ApiResult.Success -> ApiResult.Success(result.data)
                    else -> result
                }
                state.value = movies
            }
        }
    }
}