package io.github.chhabra_dhiraj.dineoutrec.data.repository

import io.github.chhabra_dhiraj.dineoutrec.data.mapper.toNoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.data.mapper.toVenueSection
import io.github.chhabra_dhiraj.dineoutrec.data.remote.NoVenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.RestaurantsApi
import io.github.chhabra_dhiraj.dineoutrec.data.remote.SectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueSectionDto
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.RestaurantsRepository
import io.github.chhabra_dhiraj.dineoutrec.domain.util.DataError
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor(
    private val api: RestaurantsApi
) : RestaurantsRepository {

    // TODO: Revisit this and check if it is scalable and makes sense when
    //  getVenueCategorySection is introduced as both would be returning 'Section' data
    override suspend fun getVenueSection(
        latitude: Double,
        longitude: Double
    ): Result<Section, DataError.Network> {
        return try {

            val restaurants = api.getRestaurants(
                latitude = latitude,
                longitude = longitude
            )

            val venueSection = (restaurants.sections
                .find { sectionDto: SectionDto ->
                    sectionDto is VenueSectionDto
                } as? VenueSectionDto)?.toVenueSection()

            // TODO: Revisit this
            val section = venueSection ?: run {
                (restaurants.sections
                    .first { sectionDto: SectionDto ->
                        sectionDto is NoVenueSectionDto
                    } as NoVenueSectionDto).toNoVenueSection()
            }
            Result.Success(
                data = section
            )
        } catch (e: Exception) {
            getExceptionError(e)
        }
    }

    // TODO: Revisit the comment here. Also, revisit if the "D" should be in or out here
    /**
     * This is added in case more func are added in future for a real-world scenario should this
     * assignment be scaled.
     **/
    private fun <D> getExceptionError(e: Exception): Result<D, DataError.Network> {
        e.printStackTrace()
        return when (e) {
            is IOException -> Result.Error(DataError.Network.NO_INTERNET)
            is HttpException -> {
                when (e.code()) {
                    408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                    else -> Result.Error(DataError.Network.UNKNOWN)
                }
            }

            else -> Result.Error(DataError.Network.UNKNOWN)
        }
    }
}