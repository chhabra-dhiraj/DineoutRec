package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.Serializable

// TODO: Revisit this comment at the end
/**
Note - This is created should the project needs to be scaled in the future in a real-world scenario
(i.e. for convenience) because there are two section item types depending on the content_type
field of a section as per the web api endpoint: Venue (content_type field not present) and
Venue Category (content_type field value = venue_category). Since only VenueItemDto is needed
for this assignment, this abstract class is left with no fields. Else common fields can be added
here (which are there in the api response, but for this assignment, they are not required, so not
added). And since there are common fields, this is made an abstract class, and not an interface.
 */
@Serializable
abstract class SectionItemDto