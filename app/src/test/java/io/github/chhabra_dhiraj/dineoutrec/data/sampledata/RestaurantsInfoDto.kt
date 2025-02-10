package io.github.chhabra_dhiraj.dineoutrec.data.sampledata

import io.github.chhabra_dhiraj.dineoutrec.data.remote.RestaurantsInfoDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.SectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueImageDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueItemDto
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section

fun getRestaurantsDtoWithVenueSectionDto() = RestaurantsInfoDto(
    sections = listOf(
        SectionDto.VenueSectionDto(
            title = "All restaurants",
            template = Section.Companion.TEMPLATE.VENUE_SECTION.value,
            items = listOf(
                VenueItemDto(
                    image = VenueImageDto(
                        url = "https://imageproxy.wolt.com/assets/673208c8479a971266a698b3"
                    ),
                    venue = VenueDto(
                        id = "1",
                        name = "Restaurant Name",
                        shortDescription = "Short Description"
                    )
                ),
                VenueItemDto(
                    image = VenueImageDto(
                        url = "https://imageproxy.wolt.com/assets/673208c8479a971266a698b3"
                    ),
                    venue = VenueDto(
                        id = "2",
                        name = "Restaurant Name",
                        shortDescription = "Short Description"
                    )
                )
            )
        ),
        SectionDto.NoVenueSectionDto(
            title = "There aren’t any restaurants on Wolt near you yet",
            template = Section.Companion.TEMPLATE.NO_VENUE_SECTION.value,
            description = "It’s not you, it’s us! We’re working hard to expand " +
                    "and hope to come to your area soon"
        )
    )
)

fun getRestaurantsDtoWithoutVenueSectionDto() = RestaurantsInfoDto(
    sections = listOf(
        SectionDto.NoVenueSectionDto(
            title = "There aren’t any restaurants on Wolt near you yet",
            template = Section.Companion.TEMPLATE.NO_VENUE_SECTION.value,
            description = "It’s not you, it’s us! We’re working hard to expand " +
                    "and hope to come to your area soon"
        )
    )
)