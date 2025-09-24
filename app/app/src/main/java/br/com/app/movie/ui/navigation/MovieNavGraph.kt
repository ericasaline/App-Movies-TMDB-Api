package br.com.app.movie.ui.navigation

import androidx.compose.runtime.Composable
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
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.TESTE) {

        composable(route = Routes.TESTE) {
            Testecreen(
                onClickSearch = {},
                onClickTryAgain = {}
            )
        }

        composable(route = Routes.HOME_SCREEN) {
            HomeScreen(
                onClickSearch = {},
                onClickTryAgain = {}
            )
        }
    }
}