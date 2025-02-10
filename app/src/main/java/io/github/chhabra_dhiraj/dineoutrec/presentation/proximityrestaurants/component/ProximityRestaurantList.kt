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
import io.github.chhabra_dhiraj.dineoutrec.domain.sampledata.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.ProximityRestaurantsEvent
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme

@Composable
fun ProximityRestaurantList(
    restaurants: List<VenueItem>,
    onEvent: (ProximityRestaurantsEvent) -> Unit
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
                items = restaurants,
                key = { _, restaurant ->
                    restaurant.venue.id
                }
            ) { index, restaurant ->
                ProximityRestaurantListItem(
                    restaurant = restaurant,
                    isLastItem = (index == restaurants.lastIndex),
                    onEvent = onEvent
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProximityRestaurantList_Preview() {
    DineoutRecTheme {
        ProximityRestaurantList(
            restaurants = getSampleRestaurantList(),
            onEvent = { }
        )
    }
}