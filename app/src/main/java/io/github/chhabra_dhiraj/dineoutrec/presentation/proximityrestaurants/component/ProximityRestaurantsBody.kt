package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.domain.model.NoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.domain.sampledata.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.EmptyListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.ErrorListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.ListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.LoadingListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.ProximityRestaurantsEvent
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.ProximityRestaurantsState
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme
import io.github.chhabra_dhiraj.dineoutrec.presentation.util.UiText

@Composable
fun ProximityRestaurantsBody(
    modifier: Modifier = Modifier,
    state: ProximityRestaurantsState,
    onEvent: (ProximityRestaurantsEvent) -> Unit
) {
    Box(modifier = modifier) {
        state.proximityRestaurants?.let { restaurants ->
            if (restaurants.isNotEmpty()) {
                ProximityRestaurantList(
                    restaurants = restaurants,
                    onEvent = onEvent
                )
            } else {
                ListPlaceholder(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            dimensionResource(
                                id = R.dimen.spacing32
                            )
                        ),
                    placeholder = {
                        EmptyListPlaceholder(
                            modifier = Modifier.fillMaxSize(),
                            // TODO: Refactor this by introducing uiState
                            title = state.noVenueSection?.title ?: "",
                            subtitle = state.noVenueSection?.description ?: "",
                            onRetry = {
                                onEvent(
                                    ProximityRestaurantsEvent
                                        .OnManualRefreshProximityRestaurants
                                )
                            }
                        )
                    })
            }
        } ?: run {
            if (state.isLoading) {
                ListPlaceholder(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            dimensionResource(
                                id = R.dimen.spacing32
                            )
                        ),
                    placeholder = {
                        LoadingListPlaceholder(modifier = Modifier.fillMaxSize())
                    })
            } else {
                state.error?.let {
                    ListPlaceholder(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                dimensionResource(
                                    id = R.dimen.spacing32
                                )
                            ),
                        placeholder = {
                            ErrorListPlaceholder(
                                modifier = Modifier.fillMaxSize(),
                                error = it.asString(),
                                onRetry = {
                                    onEvent(
                                        ProximityRestaurantsEvent
                                            .OnManualRefreshProximityRestaurants
                                    )
                                }
                            )
                        })
                }
            }
        }
    }
}


// For Proximity Restaurants Body
@Preview(showBackground = true)
@Composable
private fun ProximityRestaurantsBody_Preview() {
    DineoutRecTheme {
        ProximityRestaurantsBody(
            state = ProximityRestaurantsState(
                proximityRestaurants = getSampleRestaurantList()
            ),
            onEvent = {}
        )
    }
}

// For Empty Proximity Restaurants Body
@Preview(showBackground = true)
@Composable
private fun EmptyProximityRestaurantsBody_Preview() {
    DineoutRecTheme {
        ProximityRestaurantsBody(
            state = ProximityRestaurantsState(
                proximityRestaurants = emptyList(),
                noVenueSection = NoVenueSection(
                    // TODO: Review this comment
                    // Hardcoding, as this is sample. Real comes from the network.
                    title = "title",
                    description = "description"
                )
            ),
            onEvent = {}
        )
    }
}

// For Loading Proximity Restaurants Body
@Preview(showBackground = true)
@Composable
private fun LoadingProximityRestaurantsBody_Preview() {
    DineoutRecTheme {
        ProximityRestaurantsBody(
            state = ProximityRestaurantsState(
                isLoading = true
            ),
            onEvent = {}
        )
    }
}

// For Error Proximity Restaurants Body
@Preview(showBackground = true)
@Composable
private fun ErrorProximityRestaurantsBody_Preview() {
    DineoutRecTheme {
        ProximityRestaurantsBody(
            state = ProximityRestaurantsState(
                error = UiText.StringResource(
                    id = R.string.str_error_unknown
                )
            ),
            onEvent = {}
        )
    }
}