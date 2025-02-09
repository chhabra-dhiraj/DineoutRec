package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

sealed interface ProximityRestaurantsEvent {
    // TODO: Check below object warnings
    object OnLocationRefreshProximityRestaurants : ProximityRestaurantsEvent
    object OnManualRefreshProximityRestaurants : ProximityRestaurantsEvent

    // TODO: Check if there is a better name for val 'id' here
    data class OnFavouriteRestaurantClick(
        val id: String,
        val isAlreadyFavourite: Boolean
    ) : ProximityRestaurantsEvent
}