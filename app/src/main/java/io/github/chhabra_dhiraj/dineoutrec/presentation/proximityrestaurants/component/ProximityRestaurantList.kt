package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem
import io.github.chhabra_dhiraj.dineoutrec.domain.sampledata.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme

@Composable
fun ProximityRestaurantList(
    restaurants: List<VenueItem>
) {
    LazyColumn(
        contentPadding = PaddingValues(
            all = dimensionResource(
                id = R.dimen.spacing16
            )
        ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(
                id = R.dimen.spacing16
            )
        )
    ) {
        items(
            items = restaurants,
            key = { it.venue.id }
        ) { restaurant ->
            ProximityRestaurantListItem(
                restaurant = restaurant
            )
        }
    }
}

@Preview
@Composable
private fun ProximityRestaurantList_Preview() {
    DineoutRecTheme {
        ProximityRestaurantList(
            restaurants = getSampleRestaurantList()
        )
    }
}