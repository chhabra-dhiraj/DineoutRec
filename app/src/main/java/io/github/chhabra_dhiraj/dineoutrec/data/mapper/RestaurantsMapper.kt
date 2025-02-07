package io.github.chhabra_dhiraj.dineoutrec.data.mapper

import io.github.chhabra_dhiraj.dineoutrec.data.remote.NoVenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.SectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueCategorySectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueImageDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueItemDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.util.getSerializationExceptionError
import io.github.chhabra_dhiraj.dineoutrec.domain.model.NoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Venue
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueImage
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueSection
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

// TODO: Revisit the plural case of this file name - Restaurants
// TODO: Revisit this comment
/**
Note: Only the fields required for this assignment are mapped. RestaurantsMapper is a generalised
mapper name in case other fields (RestaurantsDto, for example) need to be mapped should this
project be scaled in a real-world scenario
 */
fun VenueSectionDto.toVenueSection() = VenueSection(
    title = title,
    items = items.map { venueItemDto ->
        venueItemDto.toVenueItem()
    }
)

fun NoVenueSectionDto.toNoVenueSection() = NoVenueSection(
    title = title,
    description = description
)

private fun VenueItemDto.toVenueItem() = VenueItem(
    image = image.toVenueImage(),
    venue = venue.toVenue()
)

private fun VenueImageDto.toVenueImage() = VenueImage(
    url = url
)

private fun VenueDto.toVenue() = Venue(
    id = id,
    name = name,
    shortDescription = shortDescription
)


// TODO: Revisit if const below should be internal or should be inside a separate constants class
// TODO: Revisit this comment
// TODO: Check if there are more templates available

/**
 * Four below values possible for 'template', but for this assignment, only first 3 are used
 * 1. venue-vertical-list
 * 2. banner-small
 * 3. no-content
 * 4. no-location
 **/
internal const val SECTION_CLASS_DISCRIMINATOR = "template"

// TODO: Refactor this
object SectionDtoSerializer : JsonContentPolymorphicSerializer<SectionDto>(SectionDto::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<SectionDto> {
        if (element is JsonObject) {
            return when (element[SECTION_CLASS_DISCRIMINATOR]?.jsonPrimitive?.content) {
                Section.Companion.TEMPLATE.VENUE_SECTION.value -> {
                    VenueSectionDto.serializer()
                }

                Section.Companion.TEMPLATE.VENUE_CATEGORY_SECTION.value -> {
                    VenueCategorySectionDto.serializer()
                }

                Section.Companion.TEMPLATE.NO_VENUE_SECTION.value -> {
                    NoVenueSectionDto.serializer()
                }

                else -> {
                    throw SerializationException(
                        // TODO: Refactor below method
                        element.getSerializationExceptionError(
                            jsonObjectName = "'sections' item 'template'",
                        )
                    )
                }
            }
        } else {
            throw SerializationException(
                // TODO: Refactor below method
                element.getSerializationExceptionError(
                    jsonObjectName = "'sections' item",
                )
            )
        }
    }
}
