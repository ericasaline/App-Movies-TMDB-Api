package br.com.app.movie.ui.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.app.movie.R
import br.com.app.movie.ui.model.CarouselItem
import br.com.app.movie.ui.screens.components.ItemsData
import br.com.app.movie.ui.theme.DarkGreen
import br.com.app.movie.ui.theme.LightGreen
import br.com.app.movie.ui.theme.LightOrange
import br.com.app.movie.ui.theme.Pink

@Composable
fun Testecreen(
    onClickSearch: () -> Unit,
    onClickTryAgain: () -> Unit
) {
    val scrollState = rememberScrollState()
    val shouldScroll = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
//            TopBar(
//                onClickSearch = {
//                    onClickSearch.invoke()
//                }
//            )
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





                    ItemsData(
                        sectionTitle = "Nos cinemas",
                        hasMore = true,
                        onClickSeeMore = {},
                        carousel = listOf(

                            CarouselItem(
                                id =  0,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  1,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  2,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  3,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            )
                        )


                    )


                    ItemsData(
                        sectionTitle = "Em Breve",
                        hasMore = true,
                        onClickSeeMore = {},
                        carousel = listOf(

                            CarouselItem(
                                id =  0,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  1,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  2,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  3,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            )
                        )


                    )


                    ItemsData(
                        sectionTitle = "Mais Avaliados",
                        carousel = listOf(

                            CarouselItem(
                                id =  0,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  1,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  2,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  3,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            )
                        )


                    )


                    ItemsData(
                        sectionTitle = "Popular",
                        carousel = listOf(

                            CarouselItem(
                                id =  0,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  1,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  2,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            ),
                            CarouselItem(
                                id =  3,
                                imageRes = R.drawable.ic_launcher_background,
                                contentDescription = ""
                            )
                        )


                    )


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