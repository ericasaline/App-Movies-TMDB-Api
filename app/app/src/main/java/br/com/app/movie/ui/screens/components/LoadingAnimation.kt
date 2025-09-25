package br.com.app.movie.ui.screens.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingAnimation(
    animationSpec: LottieCompositionSpec,
    modifier: Modifier = Modifier,
    interactions: Int = Integer.MAX_VALUE,
    speed: Float = 1.0F,
    isPlaying: Boolean = true
) {
    val composition by rememberLottieComposition(animationSpec)
    val progress by animateLottieCompositionAsState(
        composition = composition,
        speed = speed,
        iterations = interactions,
        isPlaying = isPlaying
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Preview
@Composable
private fun LoadingAnimationPreview() {
    LoadingAnimation(
        modifier = Modifier.size(120.dp),
        animationSpec = LottieCompositionSpec.RawRes(R.raw.movie)
    )
}