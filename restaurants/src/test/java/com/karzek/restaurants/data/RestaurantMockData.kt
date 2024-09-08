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

internal const val testLatitude: String = "60.170187"
internal const val testLongitude: String = "24.930599"
internal const val testLimit: Int = 3

internal val testRestaurantPageDto = RestaurantPageDto(
  sections = listOf(
    CategoriesSectionDto(NAME_SECTION_CATEGORY),
    VenuesSectionDto(
      name = NAME_SECTION_VENUES,
      items = listOf(
        VenueSectionItemDto(
          image = ImageDto("https://imageproxy.wolt.com/mes-image/8695de58-c638-437d-a314-ad0ee5bc530f/2c3dbb05-f61a-429a-9338-b5c22f38b408"),
          venue = VenueDto(
            id = "5ae6013cf78b5a000bb64022",
            name = "McDonald's Helsinki Kamppi",
            shortDescription = "I'm lovin' it.",
          )
        ),
        VenueSectionItemDto(
          image = ImageDto("https://prod-wolt-venue-images-cdn.wolt.com/5cc175b2daaaee24fdbb92ee/0746696e-0045-11ee-8c69-2abb24723ee4_taco_bell.png"),
          venue = VenueDto(
            id = "5cc175b2daaaee24fdbb92ee",
            name = "Taco Bell Tennispalatsi",
            shortDescription = "Meksikolainen pikaruokaravintola",
          )
        ),
        VenueSectionItemDto(
          image = ImageDto("https://imageproxy.wolt.com/mes-image/d1fe2e7c-4504-48b0-9805-c9e2d115d740/581d0028-c846-478f-8d0e-da27c9d1465b"),
          venue = VenueDto(
            id = "630723a28b2af6d016acbd64",
            name = "KotKot Tennispalatsi",
            shortDescription = "We Kot you.",
          )
        ),
        VenueSectionItemDto(
          image = ImageDto("https://prod-wolt-venue-images-cdn.wolt.com/5f16ec0be0c7486ca166bf37/0433a224-c313-11ed-a160-8ae4ec7e2c5f_cover.jpg"),
          venue = VenueDto(
            id = "5f16ec0be0c7486ca166bf37",
            name = "Eat Poke Kamppi",
            shortDescription = "Helsingin ensimm\u00e4inen kokonaan poke-kulhoille omistettu ravintola",
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
  Restaurant(
    id = "5ae6013cf78b5a000bb64022",
    name = "McDonald's Helsinki Kamppi",
    shortDescription = "I'm lovin' it.",
    imageUrl = "https://imageproxy.wolt.com/mes-image/8695de58-c638-437d-a314-ad0ee5bc530f/2c3dbb05-f61a-429a-9338-b5c22f38b408",
  ),
  Restaurant(
    id = "5cc175b2daaaee24fdbb92ee",
    name = "Taco Bell Tennispalatsi",
    shortDescription = "Meksikolainen pikaruokaravintola",
    imageUrl = "https://prod-wolt-venue-images-cdn.wolt.com/5cc175b2daaaee24fdbb92ee/0746696e-0045-11ee-8c69-2abb24723ee4_taco_bell.png",
  ),
  Restaurant(
    id = "630723a28b2af6d016acbd64",
    name = "KotKot Tennispalatsi",
    shortDescription = "We Kot you.",
    imageUrl = "https://imageproxy.wolt.com/mes-image/d1fe2e7c-4504-48b0-9805-c9e2d115d740/581d0028-c846-478f-8d0e-da27c9d1465b",
  ),
)

internal fun RestaurantPageDto.restaurantDtoAt(index: Int) =
  (sections[1] as VenuesSectionDto).items[index]