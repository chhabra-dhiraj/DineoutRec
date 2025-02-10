package io.github.chhabra_dhiraj.dineoutrec.data.repository

import io.github.chhabra_dhiraj.dineoutrec.data.datastore.UserPreferencesDataStore
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val userPreferencesDataStore: UserPreferencesDataStore
) : UserPreferencesRepository {

    override suspend fun getFavouriteRestaurantVenueIds() =
        userPreferencesDataStore.getFavouriteRestaurantVenueIds()

    override suspend fun saveFavouriteRestaurantVenueId(id: String) {
        userPreferencesDataStore.saveFavouriteRestaurantVenueId(id)
    }

    override suspend fun removeFavouriteRestaurantVenueId(id: String) {
        userPreferencesDataStore.removeFavouriteRestaurantVenueId(id)
    }
}