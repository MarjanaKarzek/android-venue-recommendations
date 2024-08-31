package com.karzek.restaurants.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

const val NAME_SECTION_CATEGORY = "restaurants_page_categories"
const val NAME_SECTION_VENUES = "restaurants-delivering-venues"

@JsonClass(generateAdapter = true)
data class RestaurantPageDto(
  @Json(name = "sections") val sections: List<SectionDto>
) {

  interface SectionDto {
    val name: String
  }

  @JsonClass(generateAdapter = true)
  data class CategoriesSectionDto(
    @Json(name = "name") override val name: String,
  ) : SectionDto

  @JsonClass(generateAdapter = true)
  data class VenuesSectionDto(
    @Json(name = "name") override val name: String,
    @Json(name = "items") val items: List<VenueSectionItemDto>,
  ) : SectionDto

  @JsonClass(generateAdapter = true)
  data class VenueSectionItemDto(
    @Json(name = "image") val image: ImageDto,
    @Json(name = "venue") val venue: VenueDto,
  )

  @JsonClass(generateAdapter = true)
  data class ImageDto(
    @Json(name = "url") val url: String,
  )

  @JsonClass(generateAdapter = true)
  data class VenueDto(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "short_description") val shortDescription: String?,
  )
}