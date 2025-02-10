package io.github.chhabra_dhiraj.dineoutrec.domain.model

sealed class Section {

    // This has 2 utilities:
    // 1. Help deserialize in SectionDtoSerializer (which is currently being used)
    // 2. Should the app be scaled, this enum would be used in domain model as template value
    companion object {

        private const val VENUE_SECTION_TEMPLATE = "venue-vertical-list"
        private const val VENUE_CATEGORY_SECTION_TEMPLATE = "banner-small"
        private const val NO_VENUE_SECTION_TEMPLATE = "no-content"
        private const val ERROR_INVALID_SECTION_TEMPLATE = "Unrecognised section template"

        enum class TEMPLATE(val value: String) {
            VENUE_SECTION(VENUE_SECTION_TEMPLATE),
            VENUE_CATEGORY_SECTION(VENUE_CATEGORY_SECTION_TEMPLATE),
            NO_VENUE_SECTION(NO_VENUE_SECTION_TEMPLATE);

            companion object {
                fun toTemplate(value: String) = when (value) {
                    VENUE_SECTION_TEMPLATE -> VENUE_SECTION
                    VENUE_CATEGORY_SECTION_TEMPLATE -> VENUE_CATEGORY_SECTION
                    NO_VENUE_SECTION_TEMPLATE -> NO_VENUE_SECTION
                    else -> throw Exception(ERROR_INVALID_SECTION_TEMPLATE)
                }
            }
        }
    }

    abstract val title: String

    // See 2. above
    abstract val template: TEMPLATE

    data class VenueSection(
        override val title: String,
        override val template: TEMPLATE,
        val items: List<VenueItem>
    ) : Section()

    data class NoVenueSection(
        override val title: String,
        override val template: TEMPLATE,
        val description: String
    ) : Section()
}