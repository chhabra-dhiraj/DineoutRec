package io.github.chhabra_dhiraj.dineoutrec.domain.model

data class NoVenueSection(
    override val title: String,
    val description: String
) : Section()
