package br.com.app.movie.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.app.movie.data.model.Movie
import br.com.app.movie.data.remote.api.ApiResult
import br.com.app.movie.ui.model.MovieItem
import br.com.app.movie.ui.model.MovieSection
import br.com.app.movie.ui.model.SortOption
import br.com.app.movie.ui.screens.components.ErrorAlert
import br.com.app.movie.ui.screens.components.GridItems
import br.com.app.movie.ui.screens.components.Loading
import br.com.app.movie.ui.screens.components.SortButton
import br.com.app.movie.ui.screens.components.TopBar
import br.com.app.movie.ui.theme.LightGreen

@Composable
fun NowComingScreen(
    nowPlayingState: ApiResult<List<Movie>>,
    upcomingState: ApiResult<List<Movie>>,
    mapMovieToGrid: (movies: List<Movie>, sortOption: SortOption) -> List<MovieItem>,
    onClickSearch: () -> Unit,
    onClickBack: () -> Unit,
) {
    var selectedTab by remember { mutableStateOf(MovieSection.NOW_PLAYING) }
    var selectedSortOption by remember { mutableStateOf(SortOption.OTHER) }
    val result = if (selectedTab == MovieSection.UPCOMING) upcomingState else nowPlayingState

    Scaffold(
        topBar = {
            TopBar(
                playAnimation = result !is ApiResult.Error,
                animationInteractions = Int.MAX_VALUE,
                hasBackIcon = result !is ApiResult.Error,
                onClickBack = { onClickBack.invoke() },
                onClickSearch = { onClickSearch.invoke() },
            )
        },
        floatingActionButton = {
            if (result !is ApiResult.Error) {
                SortButton(
                    onSortByDate = { selectedSortOption = SortOption.DATE },
                    onSortByName = { selectedSortOption  = SortOption.NAME },
                    onSortByRating = { selectedSortOption = SortOption.RATING }
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .background(LightGreen)
                    .padding(paddingValues)
                    .fillMaxSize(),
                content = {
                    when (result) {
                        is ApiResult.Error -> ErrorAlert(
                            onClickBack = {
                                onClickBack.invoke()
                            }
                        )

                        ApiResult.Loading -> Loading()

                        is ApiResult.Success -> {
                            val movieList = mapMovieToGrid.invoke(result.data, selectedSortOption)

                            GridItems(
                                movieList = movieList,
                                onClickNowPlaying = { selectedTab = MovieSection.NOW_PLAYING },
                                onClickUpcoming = { selectedTab = MovieSection.UPCOMING },
                                orderBy = selectedSortOption
                            )
                        }
                    }
                }
            )
        }
    )
}