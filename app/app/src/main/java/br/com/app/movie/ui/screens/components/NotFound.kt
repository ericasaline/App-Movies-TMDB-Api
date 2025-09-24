package br.com.app.movie.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import br.com.app.movie.ui.theme.DarkGreen
import br.com.app.movie.ui.theme.LightGreen
import com.airbnb.lottie.compose.LottieCompositionSpec

@Composable
fun NotFound(
    searchText: String,
    onClickHome: () -> Unit,
    onClickNowPlaying: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
                top = 48.dp
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        content = {
            Text(
                text= stringResource(R.string.text_not_found).format(searchText),
                color = DarkGreen,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                LoadingAnimation(
                    modifier = Modifier.fillMaxSize(),
                    animationSpec = LottieCompositionSpec.RawRes(R.raw.notfound)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Button(
                        modifier = Modifier.wrapContentWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkGreen
                        ),
                        onClick = {
                            onClickHome.invoke()
                        },
                        content = {
                            Icon(
                                modifier = Modifier.padding(10.dp),
                                imageVector = Icons.Filled.Home,
                                contentDescription = stringResource(R.string.text_button_description_not_found),
                                tint = LightGreen
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(
                        modifier = Modifier.wrapContentWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkGreen
                        ),
                        onClick = {
                            onClickNowPlaying.invoke()
                        },
                        content = {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = stringResource(R.string.text_now_playing),
                                color = LightGreen,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun NotFoundPreview() {
    NotFound(
        searchText = "Batman",
        onClickHome = {},
        onClickNowPlaying = {}
    )
}