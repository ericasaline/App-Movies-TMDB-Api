package br.com.app.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.app.movie.ui.navigation.MovieNavGraph
import br.com.app.movie.ui.theme.MovieTheme
import br.com.app.movie.ui.viewmodel.MovieViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieTheme {
                val movieViewModel = koinViewModel<MovieViewModel>()
                MovieNavGraph(
                    viewModel = movieViewModel
                )
            }
        }
    }
}