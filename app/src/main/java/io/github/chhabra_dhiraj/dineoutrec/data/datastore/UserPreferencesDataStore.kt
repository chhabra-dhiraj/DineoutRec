package io.github.chhabra_dhiraj.dineoutrec.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

// TODO: Check if it makes sense to have an interface for this
class UserPreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private const val ERROR_FETCHING_RESTAURANTS_USER_PREFERENCES =
            "User Preferences fetch error in Restaurants Data Store"

        // TODO: Check if this needs to be here at all
        private const val FAVOURITE_RESTAURANTS_KEY = "favourite_restaurants"
    }

    // TODO: Check if there needs to be a val for this
    private val FAVOURITE_RESTUARANTS = stringSetPreferencesKey(FAVOURITE_RESTAURANTS_KEY)

    // TODO: Check if there is any better way than this
    suspend fun getFavouriteRestaurantsIds(): List<String> =
        dataStore.data.map { preferences ->
            preferences[FAVOURITE_RESTUARANTS] ?: emptySet()
        }.firstOrNull()?.toList() ?: run {
            // TODO: Check if there is a better way for this
            // TODO: Revisit this comment
            // Logging the error instead of throwing an exception to not block the user from seeing
            // the restaurants just, in case, the data store did not fetch the data internally
            // which is rare
            Timber.e(ERROR_FETCHING_RESTAURANTS_USER_PREFERENCES)
            emptyList()
        }


    suspend fun saveFavouriteRestaurantsId(id: String) {
        dataStore.edit { preferences ->
            // TODO: Check what is the right way to check if this operation was successful
            val currentList = preferences[FAVOURITE_RESTUARANTS]?.toMutableSet() ?: mutableSetOf()
            currentList.add(id)
            // TODO: Check if this step is needed
            preferences[FAVOURITE_RESTUARANTS] = currentList
        }
    }

    suspend fun removeFavouriteRestaurantsId(id: String) {
        dataStore.edit { preferences ->
            // TODO: Check what is the right way to check if this operation was successful
            val currentList = preferences[FAVOURITE_RESTUARANTS]?.toMutableSet() ?: return@edit
            currentList.remove(id)
            // TODO: Check if this step is needed
            preferences[FAVOURITE_RESTUARANTS] = currentList
        }
    }
}