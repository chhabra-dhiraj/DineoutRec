package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.chhabra_dhiraj.dineoutrec.domain.location.LocationData
import io.github.chhabra_dhiraj.dineoutrec.domain.model.NoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueSection
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.RestaurantsRepository
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result
import io.github.chhabra_dhiraj.dineoutrec.presentation.util.asErrorUiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

// TODO: Check if this is the right way
private const val LOCATION_UPDATE_TIME_SECONDS = LocationData.LOCATION_UPDATE_TIME_SECONDS

@HiltViewModel
class ProximityRestaurantsViewModel @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProximityRestaurantsState())
    val state = _state.asStateFlow()

    // TODO: Check the name of the below variable
    private var currentTime = 0 // In seconds
    private var currentLocationIndex = 0
    private val locations = LocationData.locations
    // TODO: Remove this var. Just for testing purpose
    var numTimesUpdated = 0

    init {
        viewModelScope.launch {
            loadVenueItems()
        }
        viewModelScope.launch {
            updateCurrentTimeAndTriggerLocationUpdate()
        }
    }

    private suspend fun updateCurrentTimeAndTriggerLocationUpdate() {
        while (true) {
            delay(1000) // TODO: Check if to be made a constant
            currentTime++
            Timber.e("war: currentTime - $currentTime")
            viewModelScope.launch {
                if (currentTime % LOCATION_UPDATE_TIME_SECONDS == 0) {
                    loadVenueItems()
                }
            }
        }
    }

    private suspend fun loadVenueItems() {
        updateStateToLoading()
        currentLocationIndex =
            ((currentTime / LOCATION_UPDATE_TIME_SECONDS) % locations.size)
        val currentLocation = locations[currentLocationIndex]
        Timber.e("war: currentLocationIndex - $currentLocationIndex")
        Timber.e("war: currentLocationIndex currentLocation - $currentLocation")
        when (
            val result = restaurantsRepository.getVenueSection(
                latitude = currentLocation.latitude,
                longitude = currentLocation.longitude
            )
        ) {
            is Result.Success -> {
                _state.update {
                    when (result.data) {
                        is VenueSection -> {
                            it.copy(
                                proximityRestaurants = result.data.items,
                                noVenueSection = null,
                                isLoading = false,
                                error = null
                            )
                        }

                        is NoVenueSection -> {
                            it.copy(
                                proximityRestaurants = emptyList(), // TODO: find a better way
                                noVenueSection = result.data,
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
                        proximityRestaurants = null,
                        noVenueSection = null,
                        isLoading = false,
                        error = result.asErrorUiText()
                    )
                }
            }
        }
        numTimesUpdated++
        Timber.e("war: numTimesUpdated - $numTimesUpdated")
    }

    fun onEvent(event: ProximityRestaurantsEvent) {
        when (event) {
            is ProximityRestaurantsEvent.OnManualRefreshProximityRestaurants -> {
                viewModelScope.launch {
                    loadVenueItems()
                }
            }

            is ProximityRestaurantsEvent.OnFavouriteRestaurantClick -> {
                viewModelScope.launch {
                    val id = event.id
                    if (event.isAlreadyFavourite) {
                        restaurantsRepository.removeFavouriteRestaurantsId(id)
                        updateFavourite(
                            id = id,
                            isFavourite = false
                        )
                    } else {
                        restaurantsRepository.saveFavouriteRestaurantsId(id)
                        updateFavourite(
                            id = id,
                            isFavourite = true
                        )
                    }
                }
            }
        }
    }

    private fun updateFavourite(id: String, isFavourite: Boolean) {
        _state.update { state ->
            var favouriteChangeId: String? = null
            state.proximityRestaurants?.let { venues ->
                // TODO: Check if this is the right way, and what happens in refresh cases
                venues.find {
                    id == it.venue.id
                }?.let {
                    it.isFavourite = isFavourite
                    favouriteChangeId = it.venue.id
                }
            }
            state.copy(
                favouriteChangeId = favouriteChangeId
            )
        }
    }

    private fun updateStateToLoading() {
        _state.update {
            it.copy(
                proximityRestaurants = null,
                noVenueSection = null,
                isLoading = true,
                error = null
            )
        }
    }
}