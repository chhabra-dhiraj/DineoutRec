package io.github.chhabra_dhiraj.dineoutrec.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        modifier = modifier
    ) {
        placeholder()
    }
}

@Composable
fun EmptyListPlaceholder(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onRetry: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = {
                onRetry.invoke()
            }
        ) {
            Text(
                text = stringResource(
                    id = R.string.btn_try_again
                )
            )
        }
    }
}

@Composable
fun LoadingListPlaceholder(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ErrorListPlaceholder(
    modifier: Modifier = Modifier,
    error: String,
    onRetry: () -> Unit
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = error,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.titleMedium
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = {
                onRetry.invoke()
            }
        ) {
            Text(
                text = stringResource(
                    id = R.string.btn_try_again
                )
            )
        }
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
                modifier = Modifier.fillMaxSize(),
                title = "title",
                subtitle = "subtitle",
                onRetry = {}
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
            LoadingListPlaceholder(modifier = Modifier.fillMaxSize())
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
                modifier = Modifier.fillMaxSize(),
                error = stringResource(id = R.string.str_error_unknown),
                onRetry = {}
            )
        }
    }
}