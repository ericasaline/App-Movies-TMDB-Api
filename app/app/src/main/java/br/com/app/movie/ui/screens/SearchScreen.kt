package br.com.app.movie.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.app.movie.data.model.Movie
import br.com.app.movie.ui.screens.components.SearchBar
import br.com.app.movie.ui.screens.components.TopBar
import br.com.app.movie.ui.theme.DarkGreen
import br.com.app.movie.ui.theme.LightGreen
import br.com.app.movie.ui.theme.Ocean

@Composable
fun SearchScreen(
    onClickBack: () -> Unit,
    onSearch: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                animationInteractions = Int.MAX_VALUE,
                hasBackIcon = true,
                hasSearchIcon = false,
                onClickBack = { onClickBack.invoke() },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .background(Ocean)
                    .padding(paddingValues)
                    .fillMaxSize(),
                content = {
                    Column(
                        content = {
                            SearchBar(
                                onSearch = { key ->

                                }
                            )
                        }
                    )
                }
            )
        }
    )
}