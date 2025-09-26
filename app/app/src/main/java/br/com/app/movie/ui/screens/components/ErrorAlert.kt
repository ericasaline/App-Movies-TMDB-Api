package br.com.app.movie.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import br.com.app.movie.ui.theme.DarkGreen
import br.com.app.movie.ui.theme.LightOrange
import br.com.app.movie.ui.theme.Pink
import com.airbnb.lottie.compose.LottieCompositionSpec

@Composable
fun ErrorAlert(
    onClickBack: () -> Unit
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
                text= stringResource(R.string.text_error),
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
                    animationSpec = LottieCompositionSpec.RawRes(R.raw.panda)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkGreen),
                onClick = { onClickBack.invoke() },
                content = {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = stringResource(R.string.text_button_error),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    LightOrange, Pink
                                )
                            )
                        )
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ErrorAlertPreview() {
    ErrorAlert(
        onClickBack = {}
    )
}