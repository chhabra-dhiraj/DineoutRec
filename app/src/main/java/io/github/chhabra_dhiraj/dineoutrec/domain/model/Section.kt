package io.github.chhabra_dhiraj.dineoutrec.domain.model

sealed class Section {

    // TODO: Revisit this and check whether it makes sense:
    //  1. To have enum for this at all
    //  2. To have string values here should be from strings.xml or some constants class
    companion object {
        enum class TEMPLATE(val value: String) {
            VENUE_SECTION("venue-vertical-list"),
            VENUE_CATEGORY_SECTION("banner-small"),
            NO_VENUE_SECTION("no-content")
        }
    }

    abstract val title: String
}