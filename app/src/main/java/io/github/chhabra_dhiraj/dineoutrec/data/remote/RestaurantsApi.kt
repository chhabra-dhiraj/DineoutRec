package io.github.chhabra_dhiraj.dineoutrec.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

// TODO: Revisit the plural case in this Restaurants here
interface RestaurantsApi {

    companion object HttpRoutes {
        // TODO: Revisit if pages should come here
        private const val RESTAURANTS = "pages/restaurants/"

        private const val PARAM_LATITUDE = "lat"
        private const val PARAM_LONGITUDE = "lon"
    }

    @GET(RESTAURANTS)
    suspend fun getRestaurants(
        @Path(PARAM_LATITUDE) latitude: Double,
        @Path(PARAM_LONGITUDE) longitude: Double
    ): RestaurantsDto
}