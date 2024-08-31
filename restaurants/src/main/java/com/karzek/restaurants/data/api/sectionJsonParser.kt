package com.karzek.restaurants.data.api

import com.karzek.restaurants.data.api.RestaurantPageDto.CategoriesSectionDto
import com.karzek.restaurants.data.api.RestaurantPageDto.SectionDto
import com.karzek.restaurants.data.api.RestaurantPageDto.VenuesSectionDto
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory

val sectionJsonParser: PolymorphicJsonAdapterFactory<SectionDto> =
  PolymorphicJsonAdapterFactory.of(SectionDto::class.java, "name")
    .withSubtype(CategoriesSectionDto::class.java, NAME_SECTION_CATEGORY)
    .withSubtype(VenuesSectionDto::class.java, NAME_SECTION_VENUES)