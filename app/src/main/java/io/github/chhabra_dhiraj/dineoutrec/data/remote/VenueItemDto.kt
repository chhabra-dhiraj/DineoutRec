package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: Check if it makes sense to have an abstract SectionItemDto for VenueCategorySection items
@Serializable
data class VenueItemDto(
    @SerialName("image")
    val image: VenueImageDto,
    @SerialName("venue")
    val venue: VenueDto
)