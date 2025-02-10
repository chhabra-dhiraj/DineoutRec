package io.github.chhabra_dhiraj.dineoutrec.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

private const val FAVOURITE_RESTAURANT_VENUE_IDS_KEY = "favourite_restaurants"

private const val ERROR_FETCHING_RESTAURANTS_FAVOURITE_USER_PREFERENCES =
    "User Preferences fetch error in Restaurant Favourite in Data Store"

class UserPreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val favouriteRestaurantVenueIdsPreference = stringSetPreferencesKey(
        FAVOURITE_RESTAURANT_VENUE_IDS_KEY
    )

    suspend fun getFavouriteRestaurantVenueIds(): List<String> =
        dataStore.data.map { preferences ->
            preferences[favouriteRestaurantVenueIdsPreference] ?: emptySet()
        }.firstOrNull()?.toList() ?: run {
            /*
            Logging the error instead of throwing an exception to not block the user from seeing
            the restaurants just, in case, the data store did not fetch the data internally
            which is rare
            */
            Timber.e(ERROR_FETCHING_RESTAURANTS_FAVOURITE_USER_PREFERENCES)
            emptyList()
        }


    suspend fun saveFavouriteRestaurantVenueId(id: String) {
        dataStore.edit { preferences ->
            val currentList =
                preferences[favouriteRestaurantVenueIdsPreference]?.toMutableSet() ?: mutableSetOf()
            currentList.add(id)
            preferences[favouriteRestaurantVenueIdsPreference] = currentList
        }
    }

    suspend fun removeFavouriteRestaurantVenueId(id: String) {
        dataStore.edit { preferences ->
            val currentList =
                preferences[favouriteRestaurantVenueIdsPreference]?.toMutableSet() ?: return@edit
            currentList.remove(id)
            preferences[favouriteRestaurantVenueIdsPreference] = currentList
        }
    }
}