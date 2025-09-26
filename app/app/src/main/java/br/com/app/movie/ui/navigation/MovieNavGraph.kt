package br.com.app.movie.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.app.movie.ui.navigation.routes.Routes
import br.com.app.movie.ui.screens.HomeScreen
import br.com.app.movie.ui.screens.NowComingScreen
import br.com.app.movie.ui.screens.SearchScreen
import br.com.app.movie.ui.viewmodel.MovieViewModel

@Composable
fun MovieNavGraph(
    viewModel: MovieViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME_SCREEN
    ) {
        composable(route = Routes.HOME_SCREEN) {
            LaunchedEffect(Unit) {
                viewModel.loadHome()
            }
            HomeScreen(
                isLoading = viewModel.isLoading.collectAsState(),
                sections =  viewModel.sections.collectAsState().value,
                mapMovieToCarousel = { movieSection, apiResult ->
                    viewModel.mapMovieToCarousel(section = movieSection, result = apiResult)
                },
                onClickSearch = {
                    navController.navigate(route = Routes.SEARCH_SCREEN)
                },
                onClickSeeMore = {
                    navController.navigate(route = Routes.NOW_COMING_SCREEN)
                },
                onClickWatched = {

                }
            )
        }

        composable(
            route = Routes.NOW_COMING_SCREEN,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(1500)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(1500)
                )
            }
        ) {
            LaunchedEffect(Unit) {
                viewModel.getNowPlayingAndUpcoming()
            }
            NowComingScreen(
                nowPlayingState = viewModel.nowPlayingState.collectAsState().value,
                upcomingState =  viewModel.upcomingState.collectAsState().value,
                mapMovieToGrid = { movieList, sortBy ->
                    viewModel.mapMovieToGrid(movies = movieList, sortOption = sortBy)
                },
                onClickSearch = {
                    navController.navigate(route = Routes.SEARCH_SCREEN)
                },
                onClickBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.SEARCH_SCREEN,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(1500)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(1500)
                )
            }
        ) {
            SearchScreen(
                onClickBack = {
                    navController.popBackStack()
                },
                onSearch = {

                }
            )
        }
    }
}