package br.com.app.movie.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarDefaults.InputField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.movie.R
import br.com.app.movie.ui.theme.DarkGreen
import br.com.app.movie.ui.theme.LightGreen
import br.com.app.movie.ui.theme.LightOrange
import br.com.app.movie.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
) {
    var textFieldState = TextFieldState(initialText = "")
    var searchResults = listOf("papa", "papagaio", "teste", "ostra")

    var expanded by rememberSaveable { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize(),
        content = {
            SearchBar(
                modifier = Modifier.align(Alignment.TopCenter),
                colors = SearchBarDefaults.colors(
                    containerColor = DarkGreen,
                ),
                inputField = {
                    InputField(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        query = textFieldState.text.toString(),
                        onQueryChange = {
                            textFieldState.edit {
                                replace(0, length, it)
                            }
                        },
                        onSearch = {
                            onSearch(textFieldState.text.toString())
                            expanded = false
                        },
                        expanded = expanded,
                        onExpandedChange = { isExpanded ->
                            expanded = isExpanded
                        },
                        colors = TextFieldDefaults.colors(
                            cursorColor = LightOrange,
                            focusedTextColor = LightOrange,
                        ),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                tint = Pink,
                                contentDescription = stringResource(R.string.text_description_search_icon)
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.text_movie_search),
                                style = MaterialTheme.typography.bodyLarge,
                                color = Pink
                            )
                        }
                    )
                },
                expanded = expanded,
                onExpandedChange = { isExpanded ->
                    expanded = isExpanded
                },
                content = {
                    Column(
                        modifier = Modifier.verticalScroll(scrollState),
                        content = {
                            searchResults.forEach { result ->
                                ListItem(
                                    colors = ListItemDefaults.colors(
                                        containerColor = LightGreen,
                                        overlineColor = Pink,
                                    ),
                                    headlineContent = {
                                        Text(
                                            text = result,
                                            color = DarkGreen
                                        )
                                    },
                                    modifier = Modifier
                                        .clickable {
                                            textFieldState.edit {
                                                replace(0, length, result)
                                            }
                                            expanded = false
                                        }
                                        .fillMaxWidth()
                                )
                            }
                        }
                    )
                }
            )
        }
    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(
        onSearch = {}
    )
}