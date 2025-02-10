package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.sample.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.BasicScreenHeader
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component.ProximityRestaurantVenuesListBody
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme
import io.github.chhabra_dhiraj.dineoutrec.presentation.util.UiText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProximityRestaurantVenuesScreenUI(
    state: ProximityRestaurantVenuesState,
    onEvent: (ProximityRestaurantVenuesEvent) -> Unit
) {
    val proximityRestaurantVenues = state.proximityRestaurantVenueItems
    val isFabShown = remember(proximityRestaurantVenues) {
        proximityRestaurantVenues?.isNotEmpty() ?: false
    }
    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarRefreshMessage = stringResource(
        id = R.string.str_message_refresh_restaurant_venues_list
    )
    val scope = rememberCoroutineScope()
    LaunchedEffect(state.isLoading) {
        if (state.isLoading) {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = snackBarRefreshMessage
                )
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    BasicScreenHeader(
                        modifier = Modifier
                            .fillMaxWidth(),
                        title = stringResource(id = R.string.app_name),
                    )
                })
        },
        floatingActionButton = {
            if (isFabShown) {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(
                            end = WindowInsets.systemBars.asPaddingValues()
                                .calculateEndPadding(LayoutDirection.Ltr)
                        ),
                    onClick = {
                        onEvent(
                            ProximityRestaurantVenuesEvent
                                .OnManualRefreshProximityRestaurantVenues
                        )
                    },
                    shape = RoundedCornerShape(
                        dimensionResource(
                            id = R.dimen.size_rounded_corner_shape
                        )
                    )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.baseline_refresh_24
                        ),
                        contentDescription = stringResource(
                            id = R.string.cd_refresh_restaurant_venues_list
                        )
                    )
                }
            }
        }
    ) { contentPadding ->
        ProximityRestaurantVenuesListBody(
            modifier = Modifier
                .padding(contentPadding),
            state = state,
            onEvent = onEvent
        )
    }
}

// For Proximity Restaurant Venues Screen List

@Preview(showBackground = true)
@Composable
private fun ProximityRestaurantVenuesScreenUI_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesScreenUI(
            state = ProximityRestaurantVenuesState(
                proximityRestaurantVenueItems = getSampleRestaurantList()
            ),
            onEvent = {}
        )
    }
}

// For Empty Proximity Restaurant Venues Screen List
@Preview(showBackground = true)
@Composable
private fun EmptyProximityRestaurantVenuesScreenUI_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesScreenUI(
            state = ProximityRestaurantVenuesState(
                proximityRestaurantVenueItems = emptyList(),
                noRestaurantVenueSection = Section.NoVenueSection(
                    title = stringResource(
                        id = R.string.error_empty_restaurant_venues_list_title
                    ),
                    template = Section.Companion.TEMPLATE.NO_VENUE_SECTION,
                    description = stringResource(
                        id = R.string.error_empty_restaurant_venues_list_description
                    )
                )
            ),
            onEvent = {}
        )
    }
}

// For Loading Proximity Restaurant Venues Screen List
@Preview(showBackground = true)
@Composable
private fun LoadingProximityRestaurantVenuesScreenUI_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesScreenUI(
            state = ProximityRestaurantVenuesState(
                isLoading = true
            ),
            onEvent = {}
        )
    }
}

// For Error Proximity Restaurant Venues Screen List
@Preview(showBackground = true)
@Composable
private fun ErrorProximityRestaurantVenuesScreenUI_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesScreenUI(
            state = ProximityRestaurantVenuesState(
                error = UiText.StringResource(
                    id = R.string.str_error_unknown
                )
            ),
            onEvent = {}
        )
    }
}