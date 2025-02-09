package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
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
    favouriteChangeId: String?,
    onEvent: (ProximityRestaurantsEvent) -> Unit
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
                id = R.dimen.spacing16
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
                isFavouriteChange = restaurant.venue.id == favouriteChangeId,
                isLastItem = (index == restaurants.lastIndex),
                onEvent = onEvent
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProximityRestaurantList_Preview() {
    DineoutRecTheme {
        ProximityRestaurantList(
            restaurants = getSampleRestaurantList(),
            favouriteChangeId = null,
            onEvent = { }
        )
    }
}