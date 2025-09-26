package br.com.app.movie.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import br.com.app.movie.ui.theme.DarkGreen
import br.com.app.movie.ui.theme.LightOrange
import br.com.app.movie.ui.theme.Pink
import com.airbnb.lottie.compose.LottieCompositionSpec

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onClickSearch: () -> Unit = {},
    onClickBack: () -> Unit = {},
    hasBackIcon: Boolean = false,
    hasSearchIcon: Boolean = true,
    playAnimation: Boolean = true,
    animationInteractions: Int = 3
) {
    TopAppBar(
        colors = topAppBarColors(containerColor = DarkGreen),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    if (!hasBackIcon) {
                        Text(
                            text = stringResource(R.string.text_discover),
                            color = Pink,
                            style = MaterialTheme.typography.titleLarge.copy(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Pink, LightOrange
                                    )
                                )
                            )
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (hasSearchIcon) {
                        IconButton(
                            onClick = { onClickSearch.invoke() },
                            content = {
                                Icon(
                                    modifier = Modifier.size(36.dp),
                                    imageVector = Icons.Filled.Search,
                                    tint = LightOrange,
                                    contentDescription = stringResource(R.string.text_button_description_search)
                                )
                            }
                        )
                    }
                    LoadingAnimation(
                        modifier = Modifier.fillMaxHeight(),
                        isPlaying = playAnimation,
                        interactions = animationInteractions,
                        animationSpec = LottieCompositionSpec.RawRes(R.raw.movie)
                    )
                }
            )
        },
        navigationIcon = {
            if (hasBackIcon) {
                IconButton(
                    onClick = { onClickBack.invoke() },
                    content = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = Pink,
                            contentDescription = stringResource(R.string.text_button_description_back)
                        )
                    }
                )
            }
        }
    )
}

@Preview
@Composable
private fun TopBarNoIconBackPreview() {
    TopBar()
}

@Preview
@Composable
private fun TopBarWithIconBackPreview() {
    TopBar(
        hasBackIcon = true
    )
}