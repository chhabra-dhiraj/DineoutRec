package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.domain.model.NoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.domain.sampledata.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.BasicScreenHeader
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component.ProximityRestaurantsBody
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme
import io.github.chhabra_dhiraj.dineoutrec.presentation.util.UiText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProximityRestaurantsScreenUI(
    state: ProximityRestaurantsState,
    onEvent: (ProximityRestaurantsEvent) -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()
    val onRefresh = {
        onEvent(ProximityRestaurantsEvent.OnManualRefreshProximityRestaurants)
    }
    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = onRefresh,
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(
                        top = dimensionResource(
                            // TODO: Find a better way to space down pull to refresh
                            id = R.dimen.spacing40
                        )
                    ),
                isRefreshing = state.isLoading,
                state = pullToRefreshState
            )
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .pullToRefresh(
                    state = rememberPullToRefreshState(),
                    isRefreshing = state.isLoading,
                    onRefresh = onRefresh,
                    enabled = false
                ),
            topBar = {
                TopAppBar(
                    title = {
                        BasicScreenHeader(
                            modifier = Modifier
                                .fillMaxWidth(),
                            title = stringResource(id = R.string.app_name),
                        )
                    })
            }
        ) { contentPadding ->
            ProximityRestaurantsBody(
                modifier = Modifier
                    .padding(contentPadding),
                state = state,
                onEvent = onEvent
            )
        }
    }
}

// For Proximity Restaurant List Screen
@Preview(showBackground = true)
@Composable
private fun ProximityRestaurantsScreenUI_Preview() {
    DineoutRecTheme {
        ProximityRestaurantsScreenUI(
            state = ProximityRestaurantsState(
                proximityRestaurants = getSampleRestaurantList()
            ),
            onEvent = {}
        )
    }
}

// For Empty Proximity Restaurant List
@Preview(showBackground = true)
@Composable
private fun EmptyProximityRestaurantsScreenUI_Preview() {
    DineoutRecTheme {
        ProximityRestaurantsScreenUI(
            state = ProximityRestaurantsState(
                proximityRestaurants = emptyList(),
                noVenueSection = NoVenueSection(
                    // TODO: Review this comment
                    // Hardcoding, as this is sample. Real comes from the network.
                    title = "There aren’t any restaurants on Wolt near you yet",
                    description = "It’s not you, it’s us! We’re working har" +
                            "d to expand and hope to come to your area soon"
                )
            ),
            onEvent = {}
        )
    }
}

// For Loading Proximity Restaurant List
@Preview(showBackground = true)
@Composable
private fun LoadingProximityRestaurantsScreenUI_Preview() {
    DineoutRecTheme {
        ProximityRestaurantsScreenUI(
            state = ProximityRestaurantsState(
                isLoading = true
            ),
            onEvent = {}
        )
    }
}

// For Error Proximity Restaurant List
@Preview(showBackground = true)
@Composable
private fun ErrorProximityRestaurantsScreenUI_Preview() {
    DineoutRecTheme {
        ProximityRestaurantsScreenUI(
            state = ProximityRestaurantsState(
                error = UiText.StringResource(
                    id = R.string.str_error_unknown
                )
            ),
            onEvent = {}
        )
    }
}