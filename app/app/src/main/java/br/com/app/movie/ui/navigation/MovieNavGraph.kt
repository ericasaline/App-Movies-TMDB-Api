package br.com.app.movie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.app.movie.ui.viewmodel.MovieViewModel

@Composable
fun MovieNavGraph(
    viewModel: MovieViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "") {
        composable(route = "") {

        }
    }
}