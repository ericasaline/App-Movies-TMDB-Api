package br.com.app.movie.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import br.com.app.movie.ui.model.CarouselItem

@Composable
fun ItemsData(
    carousel: List<CarouselItem>,
    sectionTitle: String,
    hasMore: Boolean = false,
    onClickSeeMore: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(16.dp),
        content = {
            Carousel(
                itemName = sectionTitle,
                carouselItems = carousel,
                hasMore = hasMore,
                onClickSeeMore = onClickSeeMore
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ItemsDataPreview() {
    ItemsData(
        sectionTitle = "Popular",
        hasMore = true,
        onClickSeeMore = {},
        carousel = List(5){
            CarouselItem(
                id =  0,
                imageRes = R.drawable.ic_launcher_background,
                contentDescription = ""
            )
        }
    )
}