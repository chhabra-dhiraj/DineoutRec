package io.github.chhabra_dhiraj.dineoutrec.data.util

object RestaurantsInfoMapperConstants {
    /**
     * Section data class discrimination is done based on the common 'template' item in api response
     * Four below values possible for 'template', but for this assignment, only first 3 are used
     * (4., for this assignment is not possible as location must be provided)
     * 1. venue-vertical-list
     * 2. banner-small
     * 3. no-content
     * 4. no-location
     **/
    const val SECTION_CLASS_DISCRIMINATOR = "template"
}