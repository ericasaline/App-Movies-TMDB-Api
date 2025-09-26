package br.com.app.movie.ui.model

import androidx.annotation.StringRes

data class MovieCarousel(
    @StringRes val title: Int,
    val hasMore: Boolean,
    val isLoading: Boolean,
    val items: List<MovieItem>
)