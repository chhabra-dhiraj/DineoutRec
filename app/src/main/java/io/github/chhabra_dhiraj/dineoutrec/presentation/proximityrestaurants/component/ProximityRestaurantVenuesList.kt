package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem
import io.github.chhabra_dhiraj.dineoutrec.domain.sample.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.ProximityRestaurantVenuesEvent
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme

@Composable
fun ProximityRestaurantVenuesList(
    restaurantVenuesList: List<VenueItem>,
    onEvent: (ProximityRestaurantVenuesEvent) -> Unit
) {
    var isVisible by remember {
        mutableStateOf(false)
    }

    // For the first time
    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it }
        ) + fadeIn()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = dimensionResource(
                    id = R.dimen.spacing16
                ),
                start = dimensionResource(
                    id = R.dimen.spacing16
                ),
                bottom = dimensionResource(
                    id = R.dimen.spacing_bottom_restaurant_list_fab
                )
            ),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(
                    id = R.dimen.spacing8
                )
            )
        ) {
            itemsIndexed(
                items = restaurantVenuesList,
                key = { _, restaurantVenueItem ->
                    restaurantVenueItem.venue.id
                }
            ) { index, restaurant ->
                ProximityRestaurantVenuesListItem(
                    restaurantVenueItem = restaurant,
                    isLastItem = (index == restaurantVenuesList.lastIndex),
                    onEvent = onEvent
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProximityRestaurantVenuesList_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesList(
            restaurantVenuesList = getSampleRestaurantList(),
            onEvent = { }
        )
    }
}