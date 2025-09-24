package br.com.app.movie.ui.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import br.com.app.movie.ui.model.PosterItem
import br.com.app.movie.ui.theme.DarkGreen
import br.com.app.movie.ui.theme.LightGreen
import br.com.app.movie.ui.theme.LightOrange
import br.com.app.movie.ui.theme.Pink

@Composable
fun ListItems(
    movieList: List<PosterItem>
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf(
        stringResource(R.string.text_now_playing),
        stringResource(R.string.text_coming)
    )

    Column(
        modifier = Modifier.padding(16.dp),
        content = {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = RoundedCornerShape(16.dp),
                            onClick = {
                                selectedIndex = index
                            },
                            selected = index == selectedIndex,
                            colors = SegmentedButtonDefaults.colors(
                                activeContainerColor = LightOrange,
                                activeBorderColor = Pink,
                                activeContentColor = Pink,
                                inactiveContainerColor = LightGreen,
                            ),
                            border = BorderStroke(
                                width = 2.dp,
                                color = Pink
                            ),
                            label = {
                                Text(
                                    modifier = Modifier.padding(10.dp),
                                    text = label,
                                    color = DarkGreen,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            )
            Spacer(modifier = Modifier.height(22.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    items(movieList) { movie ->
                        Column(
                            content = {
                                Image(
                                    modifier = Modifier
                                        .defaultMinSize(
                                            minHeight = 180.dp,
                                            minWidth = 135.dp
                                        )
                                        .clip(RoundedCornerShape(12.dp))
                                        .align(Alignment.CenterHorizontally),
                                    contentScale = ContentScale.FillBounds,
                                    painter = painterResource(movie.poster),
                                    contentDescription = stringResource(R.string.text_description_movie_poster)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = movie.title,
                                    color = DarkGreen,
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.labelSmall
                                )
                                Spacer(modifier = Modifier.height(32.dp))
                            }
                        )
                    }
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ListItemsPreview() {
    ListItems(
        movieList = List(12) {
            PosterItem(
                id = 0,
                poster = R.drawable.ic_launcher_background,
                title = "Batman"
            )
        }
    )
}