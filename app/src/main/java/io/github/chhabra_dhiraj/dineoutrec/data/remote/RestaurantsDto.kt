package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: Revisit the plural case in this Restaurants here
@Serializable
data class RestaurantsDto(
    @SerialName("sections")
    val sections: List<SectionDto>
)
