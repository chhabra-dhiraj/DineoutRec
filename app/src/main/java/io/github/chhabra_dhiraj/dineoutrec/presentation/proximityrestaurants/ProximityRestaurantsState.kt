package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

import io.github.chhabra_dhiraj.dineoutrec.domain.model.NoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem
import io.github.chhabra_dhiraj.dineoutrec.presentation.util.UiText

data class ProximityRestaurantsState(
    val proximityRestaurants: List<VenueItem>? = null,
    val noVenueSection: NoVenueSection? = null,
    val favouriteChangeId: String? = null,
    val isLoading: Boolean = false,
    val error: UiText? = null
)
