package io.github.chhabra_dhiraj.dineoutrec.domain.model

data class VenueItem(
    val image: VenueImage,
    val venue: Venue,
    val isFavourite: Boolean = false
)