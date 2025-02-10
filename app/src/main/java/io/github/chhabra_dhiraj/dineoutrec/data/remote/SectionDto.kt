package io.github.chhabra_dhiraj.dineoutrec.data.remote

import io.github.chhabra_dhiraj.dineoutrec.data.util.RestaurantsInfoMapperConstants.SECTION_CLASS_DISCRIMINATOR
import io.github.chhabra_dhiraj.dineoutrec.data.util.getSerializationExceptionError
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive


@Serializable(with = SectionDtoSerializer::class)
sealed class SectionDto {

    companion object {
        // Only first 15 items to be displayed, as per the assignment
        private const val NUM_DISPLAY_VENUE_ITEMS = 15
    }

    @SerialName("title")
    abstract val title: String

    @SerialName("template")
    abstract val template: String

    @Serializable
    data class VenueSectionDto(
        override val title: String,
        override val template: String,
        @SerialName("items")
        private val items: List<VenueItemDto>
    ) : SectionDto() {
        fun getAllowedVenueItemDtos() = items.take(NUM_DISPLAY_VENUE_ITEMS)
    }

    @Serializable
    data class NoVenueSectionDto(
        override val title: String,
        override val template: String,
        @SerialName("description")
        val description: String
    ) : SectionDto()

    // This class is necessary to create for class discrimination, however, for this assignment,
    // only VenueSectionDto and NoVenueSectionDto is needed
    @Serializable
    data class VenueCategorySectionDto(
        override val title: String,
        override val template: String,
    ) : SectionDto()
}

object SectionDtoSerializer : JsonContentPolymorphicSerializer<SectionDto>(SectionDto::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<SectionDto> {
        if (element is JsonObject) {
            return when (element[SECTION_CLASS_DISCRIMINATOR]?.jsonPrimitive?.content) {
                Section.Companion.TEMPLATE.VENUE_SECTION.value -> {
                    SectionDto.VenueSectionDto.serializer()
                }

                Section.Companion.TEMPLATE.VENUE_CATEGORY_SECTION.value -> {
                    SectionDto.VenueCategorySectionDto.serializer()
                }

                Section.Companion.TEMPLATE.NO_VENUE_SECTION.value -> {
                    SectionDto.NoVenueSectionDto.serializer()
                }

                else -> {
                    throw SerializationException(
                        "'sections' item field 'template'".getSerializationExceptionError()
                    )
                }
            }
        } else {
            throw SerializationException(
                "'sections' item".getSerializationExceptionError()
            )
        }
    }
}