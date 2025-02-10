package io.github.chhabra_dhiraj.dineoutrec.data.repository

import io.github.chhabra_dhiraj.dineoutrec.data.mapper.toNoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.data.mapper.toVenueSection
import io.github.chhabra_dhiraj.dineoutrec.data.remote.NoVenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.RestaurantsApi
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.domain.location.LocationData
import io.github.chhabra_dhiraj.dineoutrec.domain.model.NoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueSection
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.RestaurantsRepository
import io.github.chhabra_dhiraj.dineoutrec.domain.util.DataError
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result
import io.github.chhabra_dhiraj.dineoutrec.sampledata.getRestaurantsDtoWithVenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.sampledata.getRestaurantsDtoWithoutVenueSectionDto
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.HttpException

class RestaurantsRepositoryImplTest {
    private lateinit var restaurantsApi: RestaurantsApi
    private lateinit var restaurantsRepository: RestaurantsRepository

    @Before
    fun setup() {
        restaurantsApi = mock(RestaurantsApi::class.java)
        restaurantsRepository = RestaurantsRepositoryImpl(
            restaurantsApi = restaurantsApi
        )
    }

    @Test
    fun `Venue Section Success`() = runBlocking {
        val restaurantsDto = getRestaurantsDtoWithVenueSectionDto()

        val location = LocationData.locations[0]
        `when`(
            restaurantsApi.getRestaurants(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenReturn(
            restaurantsDto
        )

        val result = restaurantsRepository.getVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )
        val venueSection = (restaurantsDto.sections.first {
            it is VenueSectionDto
        } as VenueSectionDto).toVenueSection()
        assert(((result as? Result.Success)?.data as? VenueSection) == venueSection)
    }

    @Test
    fun `No Venue Section Success`() = runBlocking {
        val restaurantsDto = getRestaurantsDtoWithoutVenueSectionDto()

        val location = LocationData.locations[0]
        `when`(
            restaurantsApi.getRestaurants(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenReturn(
            restaurantsDto
        )

        val result = restaurantsRepository.getVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )

        val noVenueSection = (restaurantsDto.sections.first { sectionDto ->
            sectionDto is NoVenueSectionDto
        } as NoVenueSectionDto).toNoVenueSection()
        assert(((result as? Result.Success)?.data as? NoVenueSection) == noVenueSection)
    }

    @Test
    fun `Section Error`() = runBlocking {
        val exception = mock(HttpException::class.java)
        val location = LocationData.locations[0]

        `when`(
            restaurantsApi.getRestaurants(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenThrow(exception)

        val result = restaurantsRepository.getVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )
        assert(result is Result.Error)
    }

    @Test
    fun `Section Error - Request Timeout`() = runBlocking {
        val httpException = mock(HttpException::class.java)
        val location = LocationData.locations[0]

        `when`(httpException.code()).thenReturn(408)
        `when`(
            restaurantsApi.getRestaurants(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenThrow(httpException)

        val result = restaurantsRepository.getVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )
        assert((result as? Result.Error)?.error == DataError.Network.REQUEST_TIMEOUT)
    }

    @Test
    fun `Section Error - Unknown Error`() = runBlocking {
        val httpException = mock(HttpException::class.java)
        val location = LocationData.locations[0]

        `when`(httpException.code()).thenReturn(409)
        `when`(
            restaurantsApi.getRestaurants(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenThrow(httpException)

        val result = restaurantsRepository.getVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )
        assert((result as? Result.Error)?.error == DataError.Network.UNKNOWN)
    }
}