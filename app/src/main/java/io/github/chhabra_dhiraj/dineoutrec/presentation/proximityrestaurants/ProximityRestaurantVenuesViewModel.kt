package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.chhabra_dhiraj.dineoutrec.domain.location.LocationData
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.RestaurantsRepository
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.UserPreferencesRepository
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result
import io.github.chhabra_dhiraj.dineoutrec.presentation.util.asErrorUiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProximityRestaurantVenuesViewModel @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProximityRestaurantVenuesState())
    val state = _state.asStateFlow()

    private var currentTime = 0 // In seconds
    private var currentLocationIndex = 0
    private val locations = LocationData.getLocationList()
    private val locationUpdateTime = LocationData.LOCATION_UPDATE_TIME_SECONDS

    init {
        loadRestaurantVenueItems()
        updateCurrentTimeAndRefreshRestaurantVenues()
    }

    private fun updateCurrentTimeAndRefreshRestaurantVenues() {
        viewModelScope.launch {
            while (true) {
                delay(1000) // 1 second delay
                currentTime++
                if (currentTime % locationUpdateTime == 0) {
                    loadRestaurantVenueItems()
                }
            }
        }
    }

    private fun loadRestaurantVenueItems() {
        viewModelScope.launch {
            updateStateToLoading()
            currentLocationIndex =
                ((currentTime / locationUpdateTime) % locations.size)
            val currentLocation = locations[currentLocationIndex]
            when (
                val result = restaurantsRepository.getRestaurantsVenueSection(
                    latitude = currentLocation.latitude,
                    longitude = currentLocation.longitude
                )
            ) {
                is Result.Success -> {
                    _state.update {
                        when (val section = result.data) {
                            is Section.VenueSection -> {
                                val favouriteRestaurantVenueIds =
                                    userPreferencesRepository.getFavouriteRestaurantVenueIds()
                                val venueItems = section.items.map { venueItem ->
                                    venueItem.copy(
                                        isFavourite = venueItem.venue.id in favouriteRestaurantVenueIds
                                    )
                                }
                                it.copy(
                                    proximityRestaurantVenueItems = venueItems,
                                    noRestaurantVenueSection = null,
                                    isLoading = false,
                                    error = null
                                )
                            }

                            is Section.NoVenueSection -> {
                                it.copy(
                                    proximityRestaurantVenueItems = emptyList(),
                                    noRestaurantVenueSection = section,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }
                    }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(
                            proximityRestaurantVenueItems = null,
                            noRestaurantVenueSection = null,
                            isLoading = false,
                            error = result.asErrorUiText()
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: ProximityRestaurantVenuesEvent) {
        when (event) {
            is ProximityRestaurantVenuesEvent.OnManualRefreshProximityRestaurantVenues -> {
                loadRestaurantVenueItems()
            }

            is ProximityRestaurantVenuesEvent.OnFavouriteRestaurantVenueClick -> {
                viewModelScope.launch {
                    val id = event.favouriteClickRestaurantVenueId
                    if (event.isAlreadyFavourite) {
                        userPreferencesRepository.removeFavouriteRestaurantVenueId(id)
                    } else {
                        userPreferencesRepository.saveFavouriteRestaurantVenueId(id)
                    }

                    updateFavourite(
                        id = id,
                        isFavourite = !event.isAlreadyFavourite
                    )
                }
            }
        }
    }

    private fun updateFavourite(id: String, isFavourite: Boolean) {
        _state.update { state ->
            state.copy(
                proximityRestaurantVenueItems = state.proximityRestaurantVenueItems?.map { venueItem ->
                    if (id == venueItem.venue.id) {
                        venueItem.copy(isFavourite = isFavourite)
                    } else {
                        venueItem
                    }
                }
            )
        }
    }

    private fun updateStateToLoading() {
        _state.update {
            it.copy(
                proximityRestaurantVenueItems = null,
                noRestaurantVenueSection = null,
                isLoading = true,
                error = null
            )
        }
    }
}