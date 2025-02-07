package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.github.chhabra_dhiraj.dineoutrec.DineoutRecApp
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem
import io.github.chhabra_dhiraj.dineoutrec.domain.sampledata.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.BasicScreenInternetImage
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme

@Composable
fun ProximityRestaurantListItem(
    modifier: Modifier = Modifier,
    restaurant: VenueItem
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicScreenInternetImage(
            modifier = modifier
                .size(
                    dimensionResource(
                        id = R.dimen.height_restaurant_list_image
                    )
                )
                .clip(
                    RoundedCornerShape(
                        dimensionResource(
                            id = R.dimen.size_rounded_corner_shape
                        )
                    ),
                ),
            contentScale = ContentScale.Crop,
            imageUrl = restaurant.image.url
        )
        Column(
            modifier = Modifier
                .padding(
                    start = dimensionResource(
                        id = R.dimen.spacing16
                    )
                )
                .weight(1f)
        ) {
            Text(
                text = restaurant.venue.name,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = restaurant.venue.shortDescription ?: "", // TODO: Revisit null case
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                    alpha = 0.6f
                ),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}


@Preview
@Composable
private fun ProximityRestaurantListItem_Preview() {
    DineoutRecTheme {
        ProximityRestaurantListItem(
            restaurant = getSampleRestaurantList()[0],
        )
    }
}