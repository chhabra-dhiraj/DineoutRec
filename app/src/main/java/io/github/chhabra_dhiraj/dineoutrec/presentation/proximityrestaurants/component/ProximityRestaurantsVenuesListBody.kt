package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.sample.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.EmptyListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.ErrorListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.ListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.LoadingListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.ProximityRestaurantVenuesEvent
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.ProximityRestaurantVenuesState
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme
import io.github.chhabra_dhiraj.dineoutrec.presentation.util.UiText

@Composable
fun ProximityRestaurantVenuesListBody(
    modifier: Modifier = Modifier,
    state: ProximityRestaurantVenuesState,
    onEvent: (ProximityRestaurantVenuesEvent) -> Unit
) {
    Box(modifier = modifier) {
        state.proximityRestaurantVenueItems?.let { restaurantVenueItem ->
            if (restaurantVenueItem.isNotEmpty()) {
                ProximityRestaurantVenuesList(
                    restaurantVenuesList = restaurantVenueItem,
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
                            title = state.noRestaurantVenueSection?.title ?: stringResource(
                                id = R.string.error_empty_restaurant_venues_list_title
                            ),
                            subtitle = state.noRestaurantVenueSection?.description ?: stringResource(
                                id = R.string.error_empty_restaurant_venues_list_description
                            ),
                            onRetry = {
                                onEvent(
                                    ProximityRestaurantVenuesEvent
                                        .OnManualRefreshProximityRestaurantVenues
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
                                        ProximityRestaurantVenuesEvent
                                            .OnManualRefreshProximityRestaurantVenues
                                    )
                                }
                            )
                        })
                }
            }
        }
    }
}


// For Proximity Restaurant Venues List Body
@Preview(showBackground = true)
@Composable
private fun ProximityRestaurantVenuesListBody_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesListBody(
            state = ProximityRestaurantVenuesState(
                proximityRestaurantVenueItems = getSampleRestaurantList()
            ),
            onEvent = {}
        )
    }
}

// For Empty Proximity Restaurant Venues List Body
@Preview(showBackground = true)
@Composable
private fun EmptyProximityRestaurantVenuesListBody_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesListBody(
            state = ProximityRestaurantVenuesState(
                proximityRestaurantVenueItems = emptyList(),
                noRestaurantVenueSection = Section.NoVenueSection(
                    title = "title",
                    template = Section.Companion.TEMPLATE.NO_VENUE_SECTION,
                    description = "description"
                )
            ),
            onEvent = {}
        )
    }
}

// For Loading Proximity Restaurant Venues List Body
@Preview(showBackground = true)
@Composable
private fun LoadingProximityRestaurantVenuesListBody_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesListBody(
            state = ProximityRestaurantVenuesState(
                isLoading = true
            ),
            onEvent = {}
        )
    }
}

// For Error Proximity Restaurant Venues List Body
@Preview(showBackground = true)
@Composable
private fun ErrorProximityRestaurantVenuesListBody_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesListBody(
            state = ProximityRestaurantVenuesState(
                error = UiText.StringResource(
                    id = R.string.str_error_unknown
                )
            ),
            onEvent = {}
        )
    }
}