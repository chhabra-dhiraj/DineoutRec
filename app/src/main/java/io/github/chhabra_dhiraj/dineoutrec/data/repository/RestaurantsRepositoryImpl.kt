package io.github.chhabra_dhiraj.dineoutrec.data.repository

import io.github.chhabra_dhiraj.dineoutrec.data.mapper.toNoVenueSection
import io.github.chhabra_dhiraj.dineoutrec.data.mapper.toVenueSection
import io.github.chhabra_dhiraj.dineoutrec.data.remote.RestaurantsApi
import io.github.chhabra_dhiraj.dineoutrec.data.remote.SectionDto
import io.github.chhabra_dhiraj.dineoutrec.data.util.getDataExceptionError
import io.github.chhabra_dhiraj.dineoutrec.domain.model.Section
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.RestaurantsRepository
import io.github.chhabra_dhiraj.dineoutrec.domain.util.DataError
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor(
    private val restaurantsApi: RestaurantsApi,
) : RestaurantsRepository {

    override suspend fun getRestaurantsVenueSection(
        latitude: Double,
        longitude: Double
    ): Result<Section, DataError.Network> {
        return try {

            val restaurants = restaurantsApi.getRestaurantsInfo(
                latitude = latitude,
                longitude = longitude
            )

            val venueSectionDto = (restaurants.sections
                .find { sectionDto: SectionDto ->
                    sectionDto is SectionDto.VenueSectionDto
                } as? SectionDto.VenueSectionDto)

            val section = venueSectionDto?.toVenueSection() ?: run {
                /*
                Using first instead of find here so that it throws an exception because, as per the
                current api response structure and possible json values, if VenueSectionDto comes
                out to be null, then, that means, there is no content, and therefore,
                NoVenueSectionDto must be there which would help debug. It would show the user
                unknown error in that case
                */
                (restaurants.sections
                    .first { sectionDto: SectionDto ->
                        sectionDto is SectionDto.NoVenueSectionDto
                    } as SectionDto.NoVenueSectionDto).toNoVenueSection()
            }

            Result.Success(
                data = section
            )
        } catch (e: Exception) {
            e.getDataExceptionError()
        }
    }
}