package br.com.app.movie.ui.model

import androidx.annotation.StringRes
import br.com.app.movie.R

enum class SortOption(
    @StringRes val titleRes: Int?
) {
    NAME(R.string.text_name),
    DATE(R.string.text_date),
    RATING(R.string.text_rated),
    OTHER(null)
}