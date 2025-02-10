package io.github.chhabra_dhiraj.dineoutrec.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApi {

    companion object HttpRoutes {
        private const val PAGES = "pages/"
        private const val RESTAURANTS = "restaurants/"
        private const val RESTAURANTS_INFO = "$PAGES$RESTAURANTS"

        private const val PARAM_LATITUDE = "lat"
        private const val PARAM_LONGITUDE = "lon"
    }

    @GET(RESTAURANTS_INFO)
    suspend fun getRestaurantsInfo(
        @Query(PARAM_LATITUDE) latitude: Double,
        @Query(PARAM_LONGITUDE) longitude: Double
    ): RestaurantsInfoDto
}