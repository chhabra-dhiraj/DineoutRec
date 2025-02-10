package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem
import io.github.chhabra_dhiraj.dineoutrec.presentation.util.UiText

data class ProximityRestaurantVenuesState(
    val proximityRestaurantVenueItems: List<VenueItem>? = null,
    val noRestaurantVenueSection: Section.NoVenueSection? = null,
    val isLoading: Boolean = false,
    val error: UiText? = null
)
