package br.com.app.movie.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import com.airbnb.lottie.compose.LottieCompositionSpec

@Composable
fun Loading() {
    Column(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 48.dp
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        content = {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                LoadingAnimation(
                    modifier = Modifier.fillMaxSize(),
                    animationSpec = LottieCompositionSpec.RawRes(R.raw.loading)
                )
            }
        }
    )
}

@Preview
@Composable
private fun LoadingPreview() {
    Loading()
}