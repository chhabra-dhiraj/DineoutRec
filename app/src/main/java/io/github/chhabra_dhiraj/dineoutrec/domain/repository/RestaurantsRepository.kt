package io.github.chhabra_dhiraj.dineoutrec.domain.repository

import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.util.DataError
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result

interface RestaurantsRepository {

    suspend fun getRestaurantsVenueSection(
        latitude: Double,
        longitude: Double
    ): Result<Section, DataError.Network>

}