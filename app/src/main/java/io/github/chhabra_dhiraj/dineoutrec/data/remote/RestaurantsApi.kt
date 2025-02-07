package io.github.chhabra_dhiraj.dineoutrec.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

// TODO: Revisit the plural case in this Restaurants here
interface RestaurantsApi {

    companion object HttpRoutes {
        // TODO: Revisit if pages should come here and '/' should be added at the end here
        private const val RESTAURANTS = "pages/restaurants/"

        private const val PARAM_LATITUDE = "lat"
        private const val PARAM_LONGITUDE = "lon"
    }

    @GET(RESTAURANTS)
    suspend fun getRestaurants(
        @Query(PARAM_LATITUDE) latitude: Double,
        @Query(PARAM_LONGITUDE) longitude: Double
    ): RestaurantsDto
}