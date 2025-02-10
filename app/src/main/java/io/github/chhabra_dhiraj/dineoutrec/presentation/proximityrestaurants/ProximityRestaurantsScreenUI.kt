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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
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
    val proximityRestaurants = state.proximityRestaurants
    val isFabShown = remember(proximityRestaurants) {
        proximityRestaurants?.isNotEmpty() ?: false
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
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
                            ProximityRestaurantsEvent
                                .OnManualRefreshProximityRestaurants
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
                        contentDescription = stringResource(R.string.cd_refresh_restaurant_list)
                    )
                }
            }
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