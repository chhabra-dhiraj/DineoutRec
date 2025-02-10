package io.github.chhabra_dhiraj.dineoutrec.sampledata

import io.github.chhabra_dhiraj.dineoutrec.data.remote.NoVenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.RestaurantsDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueImageDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueItemDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueSectionDto

fun getRestaurantsDtoWithVenueSectionDto() = RestaurantsDto(
    sections = listOf(
        VenueSectionDto(
            title = "All restaurants",
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
        NoVenueSectionDto(
            title = "There aren’t any restaurants on Wolt near you yet",
            description = "It’s not you, it’s us! We’re working hard to expand " +
                    "and hope to come to your area soon"
        )
    )
)

fun getRestaurantsDtoWithoutVenueSectionDto() = RestaurantsDto(
    sections = listOf(
        NoVenueSectionDto(
            title = "There aren’t any restaurants on Wolt near you yet",
            description = "It’s not you, it’s us! We’re working hard to expand " +
                    "and hope to come to your area soon"
        )
    )
)