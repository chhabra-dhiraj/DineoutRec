package io.github.chhabra_dhiraj.dineoutrec.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: Check whether generic type SI should be out or in or something else
@Serializable
data class SectionDto<out SI: SectionItemDto>(
    // It's default null for the case when content_type field is not present in the api response
    @SerialName("content_type")
    val contentType: String? = null,

    @SerialName("items")
    val items: List<SI>
)
