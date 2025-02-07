package io.github.chhabra_dhiraj.dineoutrec.domain.model

data class VenueSection(
    override val title: String,
    val items: List<VenueItem>
) : Section()
