package io.github.chhabra_dhiraj.dineoutrec.domain.model

sealed class Section {

    companion object {
        enum class TEMPLATE(val value: String) {
            VENUE_SECTION("venue-vertical-list"),
            VENUE_CATEGORY_SECTION("banner-small"),
            NO_VENUE_SECTION("no-content")
        }
    }

    abstract val title: String
}