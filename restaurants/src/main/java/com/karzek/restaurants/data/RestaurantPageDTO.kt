package com.karzek.restaurants.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantPageDTO(
  @Json(name = "sections") val sections: List<SectionDTO>
) {

  interface SectionDTO {
    val name: String
  }

  @JsonClass(generateAdapter = true)
  data class CategorySectionItemDTO(
    @Json(name = "name") override val name: String,
    @Json(name = "content_id") val contentId: String,
    @Json(name = "description") val description: String,
    @Json(name = "image") val image: ItemImageDTO,
  ) : SectionDTO

  @JsonClass(generateAdapter = true)
  data class VenueSectionItemDTO(
    @Json(name = "name") override val name: String,
    @Json(name = "content_id") val contentId: String,
    @Json(name = "description") val description: String,
    @Json(name = "image") val image: ItemImageDTO,
    @Json(name = "venue") val venue: VenueDTO,
  ) : SectionDTO

  @JsonClass(generateAdapter = true)
  data class ItemImageDTO(
    @Json(name = "url") val url: String,
  )

  @JsonClass(generateAdapter = true)
  data class VenueDTO(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "short_description") val shortDescription: String,
  )
}