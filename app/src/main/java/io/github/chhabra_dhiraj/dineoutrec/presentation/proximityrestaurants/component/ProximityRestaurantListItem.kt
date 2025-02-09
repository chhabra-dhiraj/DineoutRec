package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem
import io.github.chhabra_dhiraj.dineoutrec.domain.sampledata.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.BasicScreenInternetImage
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.ProximityRestaurantsEvent
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme

@Composable
fun ProximityRestaurantListItem(
    modifier: Modifier = Modifier,
    restaurant: VenueItem,
    isFavouriteChange: Boolean = false,
    onEvent: (ProximityRestaurantsEvent) -> Unit,
    isLastItem: Boolean
) {
    Row(
        modifier = modifier
    ) {
        BasicScreenInternetImage(
            modifier = Modifier
                .padding(
                    top = dimensionResource(
                        id = R.dimen.spacing8
                    )
                )
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
            imageUrl = restaurant.image.url,
            errorDrawableId = R.drawable.baseline_fastfood_24
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(
                    // This is 104dp to keep the minimum margin from the horizontalDivider
                    // to be at least 16dp as:
                    // 104dp - 88dp (size of the internet image + its padding) = 16dp
                    minHeight = dimensionResource(
                        id = R.dimen.min_height_restaurant_list_content
                    )
                )
                .padding(
                    start = dimensionResource(
                        id = R.dimen.spacing16
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(
                            id = R.dimen.spacing8 // TODO: Check if something else
                        )
                    )
                    .padding(
                        top = dimensionResource(
                            id = R.dimen.spacing8
                        ),
                        bottom = dimensionResource(
                            id = R.dimen.spacing16
                        ),
                        end = dimensionResource(
                            id = R.dimen.spacing56 // For the favourite icon
                        )
                    )
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = restaurant.venue.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    // TODO: Check if to restrict this to 2 lines only
                    text = restaurant.venue.shortDescription ?: "", // TODO: Revisit null case
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = 0.6f
                    ),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Image(
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.spacing16)
                    )
                    .align(Alignment.CenterEnd)
                    .clickable {
                        onEvent(
                            ProximityRestaurantsEvent
                                .OnFavouriteRestaurantClick(
                                    id = restaurant.venue.id,
                                    isAlreadyFavourite = restaurant.isFavourite
                                )
                        )
                    },
                imageVector = ImageVector.vectorResource(
                    id = remember(restaurant.isFavourite) {
                        if (restaurant.isFavourite) {
                            R.drawable.baseline_favorite_24
                        } else {
                            R.drawable.baseline_favorite_border_24
                        }
                    }
                ),
                contentDescription = null, // TODO: Add this for accessibility
                alignment = Alignment.CenterEnd
            )
            if (!isLastItem) {
                HorizontalDivider(
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

// For favourite restaurant item
@Preview(showBackground = true)
@Composable
private fun FavouriteProximityRestaurantListItem_Preview() {
    DineoutRecTheme {
        ProximityRestaurantListItem(
            restaurant = getSampleRestaurantList()[0],
            isFavouriteChange = false,
            onEvent = {},
            isLastItem = false
        )
    }
}

// For non-favourite restaurant item
@Preview(showBackground = true)
@Composable
private fun NonFavouriteProximityRestaurantListItem_Preview() {
    DineoutRecTheme {
        ProximityRestaurantListItem(
            restaurant = getSampleRestaurantList()[14],
            isFavouriteChange = false,
            onEvent = {},
            isLastItem = false
        )
    }
}