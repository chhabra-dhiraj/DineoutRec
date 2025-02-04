package io.github.chhabra_dhiraj.dineoutrec.data.repository

import io.github.chhabra_dhiraj.dineoutrec.data.mapper.toVenueItem
import io.github.chhabra_dhiraj.dineoutrec.data.remote.RestaurantsApi
import io.github.chhabra_dhiraj.dineoutrec.data.remote.SectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.remote.VenueItemDto
import io.github.chhabra_dhiraj.dineoutrec.domain.model.VenueItem
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.RestaurantsRepository
import io.github.chhabra_dhiraj.dineoutrec.domain.util.DataError
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor(
    private val api: RestaurantsApi
) : RestaurantsRepository {
    override suspend fun getVenues(
        latitude: Double,
        longitude: Double
    ): Result<List<VenueItem>, DataError.Network> {
        return try {

            val restaurants = api.getRestaurants(
                latitude = latitude,
                longitude = longitude
            )

            // TODO: Check if using sequence makes sense here
            val venueItems = restaurants.sections.asSequence()
                .filter { sectionDto: SectionDto<*> ->
                    // TODO: Revisit this comment
                    /* As per the current api response, for venues, contentType is not present at
                     all, and therefore, contentType is null (default value - check SectionDto
                     comments) And below, "any" check is for the case, if there are more null
                     content types added in the future in a real-world scenario when the api is
                     scaled, the app does not crash*/
                    // TODO: check if there is a better way to check null or null could be
                    //  removed (for example, to replace it with a CONSTANT = CONTENT_TYPE_VENUE)
                    sectionDto.contentType == null && sectionDto.items.any { sectionItemDto ->
                        sectionItemDto is VenueItemDto
                    }
                }.map { sectionDto ->
                    sectionDto.items.map { sectionItemDto ->
                        (sectionItemDto as VenueItemDto).toVenueItem()
                    }
                }
                .toList()
                .flatten()

            Result.Success(
                data = venueItems
            )
        } catch (e: Exception) {
            getExceptionError(e)
        }
    }

    // TODO: Revisit the comment here
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