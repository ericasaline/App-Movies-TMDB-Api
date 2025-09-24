package br.com.app.movie.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import br.com.app.movie.ui.model.CarouselItem
import br.com.app.movie.ui.theme.DarkGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carousel(
    itemName: String,
    carouselItems: List<CarouselItem>,
    onClickSeeMore: () -> Unit = {},
    hasMore: Boolean = false
) {
    val items = remember { carouselItems }
    val carouselState = rememberCarouselState { items.count() }

    Column(
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = itemName,
                        color = DarkGreen,
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (hasMore) {
                        IconButton(
                            onClick = {
                                onClickSeeMore.invoke()
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                                    tint = DarkGreen,
                                    contentDescription = stringResource(R.string.text_see_more)
                                )
                            }
                        )
                    }
                }
            )
            HorizontalMultiBrowseCarousel(
                state = carouselState,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                preferredItemWidth = 186.dp,
                itemSpacing = 8.dp,
                content = { carouselItem ->
                    val item = items[carouselItem]

                    Image(
                        modifier = Modifier
                            .height(205.dp)
                            .maskClip(MaterialTheme.shapes.extraLarge),
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.contentDescription,
                        contentScale = ContentScale.Crop
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CarouselPreview() {
    Carousel(
        itemName = "Nos Cinemas",
        hasMore = true,
        onClickSeeMore = {},
        carouselItems = List(5) {
            CarouselItem(
                id =  0,
                imageRes = R.drawable.ic_launcher_background,
                contentDescription = ""
            )
        }
    )
}