package com.karzek.restaurants.data

import com.karzek.domain.restaurants.Restaurant
import com.karzek.restaurants.data.api.NAME_SECTION_CATEGORY
import com.karzek.restaurants.data.api.NAME_SECTION_VENUES
import com.karzek.restaurants.data.api.RestaurantPageDto
import com.karzek.restaurants.data.api.RestaurantPageDto.CategoriesSectionDto
import com.karzek.restaurants.data.api.RestaurantPageDto.ImageDto
import com.karzek.restaurants.data.api.RestaurantPageDto.VenueDto
import com.karzek.restaurants.data.api.RestaurantPageDto.VenueSectionItemDto
import com.karzek.restaurants.data.api.RestaurantPageDto.VenuesSectionDto

internal val testLatitude: String = "60.170187"
internal val testLongitude: String = "24.930599"
internal val testLimit: Int = 3
internal val testRestaurantPageDto = RestaurantPageDto(
  sections = listOf(
    CategoriesSectionDto(NAME_SECTION_CATEGORY),
    VenuesSectionDto(
      name = NAME_SECTION_VENUES,
      items = listOf(
        VenueSectionItemDto(
          image = ImageDto(""),
          venue = VenueDto(
            id = "id",
            name = "",
            shortDescription = "",
          )
        ),
        VenueSectionItemDto(
          image = ImageDto(""),
          venue = VenueDto(
            id = "id",
            name = "",
            shortDescription = "",
          )
        ),
        VenueSectionItemDto(
          image = ImageDto(""),
          venue = VenueDto(
            id = "id",
            name = "",
            shortDescription = "",
          )
        ),
        VenueSectionItemDto(
          image = ImageDto(""),
          venue = VenueDto(
            id = "id",
            name = "",
            shortDescription = "",
          )
        ),
      )
    )
  )
)
internal val testRestaurantPageDtoNoVenues = RestaurantPageDto(
  sections = listOf(CategoriesSectionDto(NAME_SECTION_CATEGORY))
)
internal val testRestaurantPageDtoNoRestaurants = RestaurantPageDto(
  sections = listOf(
    CategoriesSectionDto(NAME_SECTION_CATEGORY),
    VenuesSectionDto(
      name = NAME_SECTION_VENUES,
      items = emptyList()
    )
  )
)
internal val testRestaurants: List<Restaurant> = listOf(
  Restaurant("id", "", "", ""),
  Restaurant("id", "", "", ""),
  Restaurant("id", "", "", ""),
)

internal fun RestaurantPageDto.restaurantDtoAt(index: Int) =
  (sections[1] as VenuesSectionDto).items[index]