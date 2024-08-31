package com.karzek.restaurants.data.mapper

import com.karzek.core.mapper.Mapper
import com.karzek.restaurants.data.api.RestaurantPageDto.VenueSectionItemDto
import com.karzek.domain.restaurants.Restaurant

class RestaurantMapper : Mapper<VenueSectionItemDto, Restaurant> {
  override fun convert(data: VenueSectionItemDto): Restaurant {
    return Restaurant(
      id = data.venue.id,
      name = data.venue.name,
      shortDescription = data.venue.shortDescription,
      imageUrl = data.image.url,
    )
  }
}