package br.com.app.movie.ui.model

import androidx.annotation.StringRes
import br.com.app.movie.R

enum class MovieSection(
    @StringRes val titleRes: Int
) {
    NOW_PLAYING(R.string.text_now_playing),
    UPCOMING(R.string.text_coming),
    TOP_RATED(R.string.text_top_rated),
    POPULAR(R.string.text_popular)
}