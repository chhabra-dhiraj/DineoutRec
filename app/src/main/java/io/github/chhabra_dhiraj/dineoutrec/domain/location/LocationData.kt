package io.github.chhabra_dhiraj.dineoutrec.domain.location

data class Location(
    val latitude: Double,
    val longitude: Double
)

// TODO: Check if this should be done this way, and kept in the data package
// TODO: Revisit this comment
/**
 * In real-world, this should be coming from a device location tracker implementation class
 * in this app.
 * 10 seconds for this assignment is assumed to be a simulation time limit to continuously check if
 * user location has changed and refresh the restaurants nearby.
 * That is exactly why this is considered constant here and not stored in a local storage
 * (preferences, for example), because the actual device location would not be stored locally in a
 * real-world scenario.
 **/
object LocationData {

    const val LOCATION_UPDATE_TIME_SECONDS = 10

    val locations = listOf(
        Location(latitude = 60.169418, longitude = 24.931618),
        Location(latitude = 60.169818, longitude = 24.932906),
        Location(latitude = 60.170005, longitude = 24.935105),
        Location(latitude = 60.169108, longitude = 24.936210), // TODO: Check this
        Location(latitude = 60.168355, longitude = 24.934869),
        Location(latitude = 60.167560, longitude = 24.932562), // TODO: Check this
        Location(latitude = 60.168254, longitude = 24.931532),
        Location(latitude = 60.169012, longitude = 24.930341),
        Location(latitude = 60.170085, longitude = 24.929569)
    )
}