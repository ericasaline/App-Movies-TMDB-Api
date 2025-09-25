package br.com.app.movie.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import br.com.app.movie.R
import br.com.app.movie.ui.model.PosterItem
import br.com.app.movie.ui.screens.components.Carousel
import br.com.app.movie.ui.screens.components.TopBar
import br.com.app.movie.ui.theme.DarkGreen
import br.com.app.movie.ui.theme.LightGreen
import br.com.app.movie.ui.theme.LightOrange
import br.com.app.movie.ui.theme.Pink
import br.com.app.movie.ui.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Testecreen(
    onClickSearch: () -> Unit,
    onClickTryAgain: () -> Unit,
    viewModel: MovieViewModel
) {
    val scrollState = rememberScrollState()
    val shouldScroll = remember { mutableStateOf(true) }

    var isRefreshing = remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullToRefreshState()


    // teste
    viewModel.teste()

    Scaffold(
        topBar = {
            TopBar(
                onClickSearch = {
                }
            )
        },
        floatingActionButton = {
            if (shouldScroll.value == true) {
                ExtendedFloatingActionButton(
                    onClick = {},
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
            }

//            SortButton(
//                onSortByName = { println("Ordenar por nome") },
//                onSortByDate = { println("Ordenar por data") },
//                onSortByRating = { println("Ordenar por avaliação") }
//            )


        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .then(if (shouldScroll.value) Modifier.verticalScroll(scrollState) else Modifier)
                    .background(LightGreen)
                    .padding(paddingValues)
                    .fillMaxSize(),
                content = {

                    PullToRefreshBox(
                        isRefreshing = isRefreshing.value,
                        state = pullRefreshState,
                        onRefresh = {

                        },
                        indicator = {
                            Indicator(
                                modifier = Modifier.align(Alignment.TopCenter),
                                isRefreshing = isRefreshing.value,
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                state = pullRefreshState
                            )
                        },
                        content = {

                            Column(modifier = Modifier.fillMaxSize()) {
                                List(5) {
                                   // Text("jhcksjdhgjkdfhkj")
                                    Carousel(
                                        itemName ="TESTE",
                                        carouselItems = List(12){
                                            PosterItem()
                                        }

                                    )
                                }
                            }

                        }
                    )



//                    ItemsData(
//                        sectionTitle = "Nos cinemas",
//                        hasMore = true,
//                        onClickSeeMore = {},
//                        carousel = listOf(
//
//                            CarouselItem(
//                                id =  0,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  1,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  2,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  3,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            )
//                        )
//
//
//                    )
//
//
//                    ItemsData(
//                        sectionTitle = "Em Breve",
//                        hasMore = true,
//                        onClickSeeMore = {},
//                        carousel = listOf(
//
//                            CarouselItem(
//                                id =  0,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  1,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  2,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  3,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            )
//                        )
//
//
//                    )
//
//
//                    ItemsData(
//                        sectionTitle = "Mais Avaliados",
//                        carousel = listOf(
//
//                            CarouselItem(
//                                id =  0,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  1,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  2,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  3,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            )
//                        )
//
//
//                    )
//
//
//                    ItemsData(
//                        sectionTitle = "Popular",
//                        carousel = listOf(
//
//                            CarouselItem(
//                                id =  0,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  1,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  2,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            ),
//                            CarouselItem(
//                                id =  3,
//                                poster = R.drawable.ic_launcher_background,
//                                title = ""
//                            )
//                        )
//
//
//                    )


                   // Search()

//                    ListItems(
//                        movieList = List(12) {
//                            PosterItem(
//                                id = 0,
//                                poster = R.drawable.ic_launcher_background,
//                                title = "Batman"
//                            )
//                        }
//                    )


//                    NotFound(
//                        onClickNowPlaying = {},
//                        onClickHome = {},
//                        searchText = ""
//                    )


//                    ErrorAlert(
//                        onClickButton = {
//
//                        }
//                    )
//
//                    Loading()




                }


            )



        }
    )
}
