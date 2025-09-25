package br.com.app.movie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.app.movie.ui.navigation.routes.Routes
import br.com.app.movie.ui.screens.HomeScreen
import br.com.app.movie.ui.screens.Testecreen
import br.com.app.movie.ui.viewmodel.MovieViewModel

@Composable
fun MovieNavGraph(
    viewModel: MovieViewModel
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = Routes.HOME_SCREEN
    ) {


        composable(route = "teste") {
            Testecreen(
                onClickSearch = {},
                onClickTryAgain = {},
                viewModel
            )
        }

        composable(route = Routes.HOME_SCREEN) {
            LaunchedEffect(Unit) {
                viewModel.loadHome()
            }

            HomeScreen(
                isLoading = viewModel.isLoading.collectAsState(),sections =  viewModel.sections.collectAsState().value,
                mapMovieToCarousel = { movieSection, apiResult ->
                    viewModel.mapMovieToCarousel(movieSection, apiResult)
                },
                onClickSearch = {},
                onClickSeeMore = {},
                onClickWatched = {}
            )
        }
    }
}