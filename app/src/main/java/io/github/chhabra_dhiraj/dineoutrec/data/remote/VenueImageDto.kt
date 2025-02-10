package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VenueImageDto(
    @SerialName("url")
    val url: String
)