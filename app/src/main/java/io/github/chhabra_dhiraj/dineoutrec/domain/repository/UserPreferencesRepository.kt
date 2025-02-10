package io.github.chhabra_dhiraj.dineoutrec.domain.repository

interface UserPreferencesRepository {

    suspend fun getFavouriteRestaurantVenueIds(): List<String>

    suspend fun saveFavouriteRestaurantVenueId(id: String)

    suspend fun removeFavouriteRestaurantVenueId(id: String)
}