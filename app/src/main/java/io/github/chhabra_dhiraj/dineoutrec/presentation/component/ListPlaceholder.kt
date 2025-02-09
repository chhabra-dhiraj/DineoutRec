package io.github.chhabra_dhiraj.dineoutrec.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme

/**
 * For the case when the list is not available because of the following reasons:
 * 1. loading
 * 2. error
 * 3. empty list
 * */
@Composable
fun ListPlaceholder(
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Center
    ) {
        placeholder()
    }
}

@Composable
fun EmptyListPlaceholder(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier
                .padding(
                    top = dimensionResource(
                        id = R.dimen.spacing8
                    )
                ),
            text = subtitle,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                alpha = 0.6f
            ),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun LoadingListPlaceholder(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ErrorListPlaceholder(
    modifier: Modifier = Modifier,
    error: String
) {
    Box(modifier = modifier) {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

// For Empty List
@Preview(showBackground = true)
@Composable
private fun EmptyListPlaceholder_Preview() {
    DineoutRecTheme {
        ListPlaceholder(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    dimensionResource(
                        id = R.dimen.spacing32
                    )
                ),
        ) {
            EmptyListPlaceholder(
                title = "title",
                subtitle = "subtitle"
            )
        }
    }
}

// For Loading
@Preview(showBackground = true)
@Composable
private fun LoadingListPlaceholder_Preview() {
    DineoutRecTheme {
        ListPlaceholder(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    dimensionResource(
                        id = R.dimen.spacing32
                    )
                ),
        ) {
            LoadingListPlaceholder()
        }
    }
}

// For error
@Preview(showBackground = true)
@Composable
private fun ErrorListPlaceholder_Preview() {
    DineoutRecTheme {
        ListPlaceholder(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    dimensionResource(
                        id = R.dimen.spacing32
                    )
                ),
        ) {
            ErrorListPlaceholder(
                error = stringResource(id = R.string.str_error_unknown)
            )
        }
    }
}