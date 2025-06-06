package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VenueDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    /*
    Optional field (some restaurant venues from api response do not have this field at all),
    therefore default is null
    */
    @SerialName("short_description")
    val shortDescription: String? = null
)