package br.com.app.movie.ui.screens.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import br.com.app.movie.ui.theme.DarkGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShimmerCarousel(
    titleSection: String,
    hasMore: Boolean = false,
    onClickSeeMore: () -> Unit = {}
) {
    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )
    val shimmerColors = listOf(
        DarkGreen.copy(alpha = 0.6f),
        DarkGreen.copy(alpha = 0.4f),
        DarkGreen.copy(alpha = 0.6f)
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim.value, translateAnim.value),
        end = Offset(translateAnim.value + 200f, translateAnim.value + 200f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = titleSection,
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
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalMultiBrowseCarousel(
                state = rememberCarouselState{ 5 },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                preferredItemWidth = 186.dp,
                itemSpacing = 8.dp,
                content = {
                    Box(
                        modifier = Modifier
                            .maskClip(MaterialTheme.shapes.extraLarge)
                            .size(width = 186.dp, height = 205.dp)
                            .background(brush, shape = MaterialTheme.shapes.extraLarge)
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ShimmerCarouselPreview() {
    ShimmerCarousel(
        titleSection = "Em Breve",
        hasMore = true,
        onClickSeeMore = {}
    )
}