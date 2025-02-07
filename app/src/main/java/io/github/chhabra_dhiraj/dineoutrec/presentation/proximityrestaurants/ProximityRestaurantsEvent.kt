package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

sealed interface ProximityRestaurantsEvent {
    // TODO: Check below object warnings
    object OnLocationRefreshProximityRestaurants : ProximityRestaurantsEvent
    object OnManualRefreshProximityRestaurants : ProximityRestaurantsEvent
}