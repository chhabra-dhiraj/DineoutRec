package io.github.chhabra_dhiraj.dineoutrec.data.repository

import io.github.chhabra_dhiraj.dineoutrec.data.mapper.toNoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.data.mapper.toVenueSection
import io.github.chhabra_dhiraj.dineoutrec.data.remote.RestaurantsApi
import io.github.chhabra_dhiraj.dineoutrec.data.remote.SectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.sampledata.getRestaurantsDtoWithVenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.sampledata.getRestaurantsDtoWithoutVenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.domain.location.LocationData
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.RestaurantsRepository
import io.github.chhabra_dhiraj.dineoutrec.domain.util.DataError
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result
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

        val location = LocationData.getLocationList()[0]
        `when`(
            restaurantsApi.getRestaurantsInfo(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenReturn(
            restaurantsDto
        )

        val result = restaurantsRepository.getRestaurantsVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )
        val venueSection = (restaurantsDto.sections.first {
            it is SectionDto.VenueSectionDto
        } as SectionDto.VenueSectionDto).toVenueSection()
        assert(((result as? Result.Success)?.data as? Section.VenueSection) == venueSection)
    }

    @Test
    fun `No Venue Section Success`() = runBlocking {
        val restaurantsDto = getRestaurantsDtoWithoutVenueSectionDto()

        val location = LocationData.getLocationList()[0]
        `when`(
            restaurantsApi.getRestaurantsInfo(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenReturn(
            restaurantsDto
        )

        val result = restaurantsRepository.getRestaurantsVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )

        val noVenueSection = (restaurantsDto.sections.first { sectionDto ->
            sectionDto is SectionDto.NoVenueSectionDto
        } as SectionDto.NoVenueSectionDto).toNoVenueSection()
        assert(((result as? Result.Success)?.data as? Section.NoVenueSection) == noVenueSection)
    }

    @Test
    fun `Section Error`() = runBlocking {
        val exception = mock(HttpException::class.java)
        val location = LocationData.getLocationList()[0]

        `when`(
            restaurantsApi.getRestaurantsInfo(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenThrow(exception)

        val result = restaurantsRepository.getRestaurantsVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )
        assert(result is Result.Error)
    }

    @Test
    fun `Section Error - Request Timeout`() = runBlocking {
        val httpException = mock(HttpException::class.java)
        val location = LocationData.getLocationList()[0]

        `when`(httpException.code()).thenReturn(408)
        `when`(
            restaurantsApi.getRestaurantsInfo(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenThrow(httpException)

        val result = restaurantsRepository.getRestaurantsVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )
        assert((result as? Result.Error)?.error == DataError.Network.REQUEST_TIMEOUT)
    }

    @Test
    fun `Section Error - Unknown Error`() = runBlocking {
        val httpException = mock(HttpException::class.java)
        val location = LocationData.getLocationList()[0]

        `when`(httpException.code()).thenReturn(409)
        `when`(
            restaurantsApi.getRestaurantsInfo(
                latitude = location.latitude,
                longitude = location.longitude
            )
        ).thenThrow(httpException)

        val result = restaurantsRepository.getRestaurantsVenueSection(
            latitude = location.latitude,
            longitude = location.longitude
        )
        assert((result as? Result.Error)?.error == DataError.Network.UNKNOWN)
    }
}