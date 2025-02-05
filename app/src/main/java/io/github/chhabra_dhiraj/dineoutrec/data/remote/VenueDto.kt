package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VenueDto(
    @SerialName("id")
    val id: String, // TODO: Exception for this to be handled when this comes out to be null
    @SerialName("name")
    val name: String, // TODO: Exception for this to be handled when this comes out to be null
    @SerialName("short_description")
    val shortDescription: String // TODO: Exception for this to be handled when this comes out to be null
)