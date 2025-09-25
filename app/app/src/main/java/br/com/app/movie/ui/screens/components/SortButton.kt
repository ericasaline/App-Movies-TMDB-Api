package br.com.app.movie.ui.screens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun SortButton(
    onSortByName: () -> Unit,
    onSortByDate: () -> Unit,
    onSortByRating: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(16.dp)
        ) {
            AnimatedVisibility(
                visible = expanded,
                content = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        content = {
                            ButtonSortOption(
                                option = stringResource(R.string.text_name),
                                onClick = {
                                    onSortByName.invoke()
                                    expanded = false
                                }
                            )
                            ButtonSortOption(
                                option = stringResource(R.string.text_sort),
                                onClick = {
                                    onSortByDate.invoke()
                                    expanded = false
                                }
                            )
                            ButtonSortOption(
                                option = stringResource(R.string.text_date),
                                onClick = {
                                    onSortByRating.invoke()
                                    expanded = false
                                }
                            )
                        }
                    )
                }
            )
            ExtendedFloatingActionButton(
                onClick = { expanded = !expanded },
                containerColor = DarkGreen,
                icon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.Close else Icons.Filled.Edit,
                        tint = LightOrange,
                        contentDescription = stringResource(R.string.text_button_description_watched)
                    )
                },
                text = {
                    Text(
                        text = if (expanded) stringResource(R.string.text_close)
                            else stringResource(R.string.text_sort),
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
    }
}

@Composable
private fun ButtonSortOption(
    option: String,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        icon = {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null
            )
        },
        text = {
            Text(
                text = option,
                color = LightOrange,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        containerColor = Pink,
        contentColor = LightOrange,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
        modifier = Modifier.border(
            width = 2.dp,
            color = LightOrange,
            shape = RoundedCornerShape(16.dp)
        )
    )
}

@Preview
@Composable
private fun SortButtonPreview() {
    SortButton(
        onSortByDate = {},
        onSortByName = {},
        onSortByRating = {}
    )
}