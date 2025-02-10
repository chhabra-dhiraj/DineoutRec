package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: Check where this constant should (in companion object, for example)
private const val NUM_DISPLAY_VENUE_ITEMS = 15

@Serializable
data class VenueSectionDto(
    override val title: String,
    @SerialName("items")
    private val items: List<VenueItemDto>
) : SectionDto() {

    // TODO: Check if it could be done in api layer
    // Only first 15 items to be displayed, as per the assignment
    fun getVenueItemDtos() = items.take(NUM_DISPLAY_VENUE_ITEMS)
}
