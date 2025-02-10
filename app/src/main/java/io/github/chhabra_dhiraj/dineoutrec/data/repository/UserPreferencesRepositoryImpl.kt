package io.github.chhabra_dhiraj.dineoutrec.data.repository

import io.github.chhabra_dhiraj.dineoutrec.data.datastore.UserPreferencesDataStore
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val userPreferencesDataStore: UserPreferencesDataStore
) : UserPreferencesRepository {

    override suspend fun getFavouriteRestaurantsIds() =
        userPreferencesDataStore.getFavouriteRestaurantsIds()

    override suspend fun saveFavouriteRestaurantsId(id: String) {
        userPreferencesDataStore.saveFavouriteRestaurantsId(id)
    }

    override suspend fun removeFavouriteRestaurantsId(id: String) {
        userPreferencesDataStore.removeFavouriteRestaurantsId(id)
    }
}