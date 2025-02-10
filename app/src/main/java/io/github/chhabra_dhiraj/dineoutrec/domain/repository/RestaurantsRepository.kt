package io.github.chhabra_dhiraj.dineoutrec.domain.repository

import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.util.DataError
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result

// TODO: Revisit the plural case in this Restaurants here
interface RestaurantsRepository {

    suspend fun getVenueSection(
        latitude: Double,
        longitude: Double
    ): Result<Section, DataError.Network>

}