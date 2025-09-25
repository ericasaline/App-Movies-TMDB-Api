package br.com.app.movie.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import br.com.app.movie.R
import br.com.app.movie.data.model.Movie
import br.com.app.movie.data.remote.api.ApiResult
import br.com.app.movie.ui.model.MovieCarousel
import br.com.app.movie.ui.model.MovieSection
import br.com.app.movie.ui.screens.components.Carousel
import br.com.app.movie.ui.screens.components.ShimmerCarousel
import br.com.app.movie.ui.screens.components.TopBar
import br.com.app.movie.ui.theme.DarkGreen
import br.com.app.movie.ui.theme.LightGreen
import br.com.app.movie.ui.theme.LightOrange
import br.com.app.movie.ui.theme.Pink
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    onClickSearch: () -> Unit,
    onClickSeeMore: () -> Unit,
    onClickWatched: () -> Unit,
    isLoading: State<Boolean>,
    sections: Map<MovieSection, StateFlow<ApiResult<List<Movie>>>>,
    mapMovieToCarousel: (section: MovieSection, result: ApiResult<List<Movie>>) -> MovieCarousel
) {
    Scaffold(
        topBar = {
            TopBar(
                onClickSearch = { onClickSearch.invoke() },
                playAnimation = !isLoading.value
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onClickWatched.invoke() },
                containerColor = DarkGreen,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        tint = LightOrange,
                        contentDescription = stringResource(R.string.text_button_description_watched)
                    )
                },
                text = {
                    Text(
                        text = stringResource(R.string.text_button_watched),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    LightOrange, Pink
                                )
                            )
                        )
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(LightGreen)
                    .padding(paddingValues)
                    .fillMaxSize(),
                content = {
                    Crossfade(
                        modifier = Modifier.fillMaxSize(),
                        targetState = isLoading.value,
                        animationSpec = tween(1000),
                        content = { loading ->
                            when (loading) {
                                true -> {
                                    Column(
                                        content = {
                                            ShimmerCarousel(
                                                titleSection = stringResource(R.string.text_now_playing),
                                                hasMore = true,
                                                onClickSeeMore = { onClickSeeMore.invoke() }
                                            )
                                            ShimmerCarousel(
                                                titleSection = stringResource(R.string.text_coming),
                                                hasMore = true,
                                                onClickSeeMore = { onClickSeeMore.invoke() }
                                            )
                                            ShimmerCarousel(titleSection = stringResource(R.string.text_popular))
                                            ShimmerCarousel(titleSection = stringResource(R.string.text_top_rated))
                                        }
                                    )
                                }
                                false -> {
                                    Column(
                                        content = {
                                            sections.forEach { (section, flow) ->
                                                val movie = mapMovieToCarousel.invoke(section, flow.collectAsState().value)

                                                Carousel(
                                                    itemName = stringResource(movie.title),
                                                    carouselItems = movie.items,
                                                    hasMore = movie.hasMore,
                                                    onClickSeeMore = onClickSeeMore
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    )
                }
            )
        }
    )
}