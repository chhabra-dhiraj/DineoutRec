package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component

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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem
import io.github.chhabra_dhiraj.dineoutrec.domain.sample.getSampleRestaurantList
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.BasicAsyncImage
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.ProximityRestaurantVenuesEvent
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme

@Composable
fun ProximityRestaurantVenuesListItem(
    modifier: Modifier = Modifier,
    restaurantVenueItem: VenueItem,
    onEvent: (ProximityRestaurantVenuesEvent) -> Unit,
    isLastItem: Boolean
) {
    Row(
        modifier = modifier
    ) {
        BasicAsyncImage(
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
            imageUrl = restaurantVenueItem.image.url,
            errorDrawableResId = R.drawable.baseline_fastfood_24,
            contentDescription = stringResource(
                id = R.string.cd_restaurant_venues_list_item_image,
                restaurantVenueItem.venue.name
            ),
            errorContentDescription = stringResource(
                id = R.string.cd_restaurant_venues_list_item_error_image,
                restaurantVenueItem.venue.name
            )
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
                            id = R.dimen.spacing8
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
                    text = restaurantVenueItem.venue.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = restaurantVenueItem.venue.shortDescription ?: "",
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = 0.6f
                    ),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            val isFavourite = restaurantVenueItem.isFavourite
            Icon(
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.spacing16)
                    )
                    .align(Alignment.CenterEnd)
                    .clickable {
                        onEvent(
                            ProximityRestaurantVenuesEvent
                                .OnFavouriteRestaurantVenueClick(
                                    favouriteClickRestaurantVenueId = restaurantVenueItem.venue.id,
                                    isAlreadyFavourite = restaurantVenueItem.isFavourite
                                )
                        )
                    },
                imageVector = ImageVector.vectorResource(
                    id = remember(isFavourite) {
                        if (isFavourite) {
                            R.drawable.baseline_favorite_24
                        } else {
                            R.drawable.baseline_favorite_border_24
                        }
                    }
                ),
                contentDescription = stringResource(
                    id = R.string.cd_favourite_icon_restaurant_venues_list_item
                )
            )
            if (!isLastItem) {
                HorizontalDivider(
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

// For favourite restaurant venues list item
@Preview(showBackground = true)
@Composable
private fun FavouriteProximityRestaurantVenuesListItem_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesListItem(
            restaurantVenueItem = getSampleRestaurantList()[0],
            onEvent = {},
            isLastItem = false
        )
    }
}

// For non-favourite restaurant venues list item
@Preview(showBackground = true)
@Composable
private fun NonFavouriteProximityRestaurantVenuesListItem_Preview() {
    DineoutRecTheme {
        ProximityRestaurantVenuesListItem(
            restaurantVenueItem = getSampleRestaurantList()[14],
            onEvent = {},
            isLastItem = false
        )
    }
}