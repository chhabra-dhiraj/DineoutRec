package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VenueSectionDto(
    override val title: String,
    @SerialName("items")
    val items: List<VenueItemDto>
) : SectionDto()
