package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoVenueSectionDto(
    @SerialName("description")
    val description: String,
    override val title: String
) : SectionDto()
