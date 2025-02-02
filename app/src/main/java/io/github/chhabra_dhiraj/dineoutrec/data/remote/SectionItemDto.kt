package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.Serializable

// TODO: Revisit this comment at the end
/**
Note - This is created in case the app needs to be scaled in the future in a real-world scenario
(i.e. for convenience) because there are two section item types depending on the content_type
field of a section as per the web api endpoint: Venue (content_type field not present) and
Venue Category (content_type field value = value_category). Since only VenueItemDto is needed
for this specific assignment, this abstract class is left with no fields
*/
@Serializable
abstract class SectionItemDto