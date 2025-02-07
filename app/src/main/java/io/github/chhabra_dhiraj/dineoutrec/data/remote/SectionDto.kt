package io.github.chhabra_dhiraj.dineoutrec.data.remote

import io.github.chhabra_dhiraj.dineoutrec.data.mapper.SectionDtoSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = SectionDtoSerializer::class)
sealed class SectionDto {

    @SerialName("title")
    abstract val title: String
}

// TODO: Revisit this comment
// This class is necessary to create for class discrimination, however, for this assignment,
// only VenueSectionDto and NoVenueSectionDto is needed
@Serializable
data class VenueCategorySectionDto(override val title: String) : SectionDto()