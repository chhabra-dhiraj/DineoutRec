package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VenueImageDto(
    @SerialName("url")
    val url: String // TODO: Exception for this to be handled when this comes out to be null
)