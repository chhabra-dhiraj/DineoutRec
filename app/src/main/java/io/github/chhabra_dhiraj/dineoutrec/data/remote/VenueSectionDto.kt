package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VenueSectionDto(
    @SerialName("items")
    val items: List<VenueItemDto>,
    override val title: String
) : SectionDto()
