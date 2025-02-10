package io.github.chhabra_dhiraj.dineoutrec.domain.repository

interface UserPreferencesRepository {

    suspend fun getFavouriteRestaurantsIds(): List<String>

    suspend fun saveFavouriteRestaurantsId(id: String)

    suspend fun removeFavouriteRestaurantsId(id: String)
}