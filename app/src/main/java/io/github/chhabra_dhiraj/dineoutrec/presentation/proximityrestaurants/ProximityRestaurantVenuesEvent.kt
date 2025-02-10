package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

sealed interface ProximityRestaurantVenuesEvent {

    data object OnManualRefreshProximityRestaurantVenues : ProximityRestaurantVenuesEvent

    data class OnFavouriteRestaurantVenueClick(
        val favouriteClickRestaurantVenueId: String,
        val isAlreadyFavourite: Boolean
    ) : ProximityRestaurantVenuesEvent
}