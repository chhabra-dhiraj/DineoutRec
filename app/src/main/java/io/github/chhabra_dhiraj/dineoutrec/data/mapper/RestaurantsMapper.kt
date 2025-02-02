package io.github.chhabra_dhiraj.dineoutrec.data.mapper

import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueImageDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueItemDto
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Venue
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueImage
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem

// TODO: Revisit this comment
/**
Note: Only the fields required for this assignment are mapped. RestaurantsMapper is a generalised
mapper name in case other fields (SectionDto, for example) are needed to be mapped should this
project be scaled in a real-world scenario
 */
fun VenueItemDto.toVenueItem() = VenueItem(
    image = image.toVenueImage(),
    venue = venue.toVenue()
)

fun VenueImageDto.toVenueImage() = VenueImage(
    url = url
)

fun VenueDto.toVenue() = Venue(
    id = id,
    name = name,
    shortDescription = shortDescription
)
