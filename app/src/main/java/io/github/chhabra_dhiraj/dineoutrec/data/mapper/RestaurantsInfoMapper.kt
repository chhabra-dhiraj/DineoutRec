package io.github.chhabra_dhiraj.dineoutrec.data.mapper

import io.github.chhabra_dhiraj.dineoutrec.data.remote.SectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueImageDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueItemDto
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Venue
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueImage
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem

/**
Note: Only the fields required for this assignment are mapped. RestaurantsInfoMapper is a
generalised mapper name in case other fields (RestaurantsDto, for example) need to be mapped
should this project be scaled in a real-world scenario
 **/
fun SectionDto.VenueSectionDto.toVenueSection() = Section.VenueSection(
    title = title,
    template = Section.Companion.TEMPLATE.toTemplate(template),
    items = getAllowedVenueItemDtos().map { venueItemDto ->
        venueItemDto.toVenueItem()
    }
)

fun SectionDto.NoVenueSectionDto.toNoVenueSection() = Section.NoVenueSection(
    title = title,
    template = Section.Companion.TEMPLATE.toTemplate(template),
    description = description
)

fun VenueItemDto.toVenueItem() = VenueItem(
    image = image.toVenueImage(),
    venue = venue.toVenue()
)

private fun VenueImageDto.toVenueImage() = VenueImage(
    url = url
)

private fun VenueDto.toVenue() = Venue(
    id = id,
    name = name,
    shortDescription = shortDescription
)