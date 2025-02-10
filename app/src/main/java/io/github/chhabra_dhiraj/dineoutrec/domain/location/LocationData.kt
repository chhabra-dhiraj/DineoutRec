package io.github.chhabra_dhiraj.dineoutrec.domain.location

data class Location(
    val latitude: Double,
    val longitude: Double
)

object LocationData {

    const val LOCATION_UPDATE_TIME_SECONDS = 10

    fun getLocationList() = listOf(
        Location(latitude = 60.169418, longitude = 24.931618),
        Location(latitude = 60.169818, longitude = 24.932906),
        Location(latitude = 60.170005, longitude = 24.935105),
        Location(latitude = 60.169108, longitude = 24.936210),
        Location(latitude = 60.168355, longitude = 24.934869),
        Location(latitude = 60.167560, longitude = 24.932562),
        Location(latitude = 60.168254, longitude = 24.931532),
        Location(latitude = 60.169012, longitude = 24.930341),
        Location(latitude = 60.170085, longitude = 24.929569)
    )
}