package com.karzek.restaurants.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

const val NAME_SECTION_CATEGORY = "restaurants_page_categories"
const val NAME_SECTION_VENUES = "restaurants-delivering-venues"

@JsonClass(generateAdapter = true)
data class RestaurantPageDTO(
  @Json(name = "sections") val sections: List<SectionDTO>
) {

  interface SectionDTO {
    val name: String
  }

  @JsonClass(generateAdapter = true)
  data class CategoriesSectionDTO(
    @Json(name = "name") override val name: String,
  ) : SectionDTO

  @JsonClass(generateAdapter = true)
  data class VenuesSectionDTO(
    @Json(name = "name") override val name: String,
    @Json(name = "items") val items: List<VenueSectionItemDTO>,
  ) : SectionDTO

  @JsonClass(generateAdapter = true)
  data class VenueSectionItemDTO(
    @Json(name = "image") val image: ImageDTO,
    @Json(name = "venue") val venue: VenueDTO,
  )

  @JsonClass(generateAdapter = true)
  data class ImageDTO(
    @Json(name = "url") val url: String,
  )

  @JsonClass(generateAdapter = true)
  data class VenueDTO(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "short_description") val shortDescription: String?,
  )
}