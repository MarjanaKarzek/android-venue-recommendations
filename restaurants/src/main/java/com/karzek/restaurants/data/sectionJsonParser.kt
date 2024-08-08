package com.karzek.restaurants.data

import com.karzek.restaurants.data.RestaurantPageDTO.CategoriesSectionDTO
import com.karzek.restaurants.data.RestaurantPageDTO.SectionDTO
import com.karzek.restaurants.data.RestaurantPageDTO.VenuesSectionDTO
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory

val sectionJsonParser: PolymorphicJsonAdapterFactory<SectionDTO> =
  PolymorphicJsonAdapterFactory.of(SectionDTO::class.java, "name")
    .withSubtype(CategoriesSectionDTO::class.java, NAME_SECTION_CATEGORY)
    .withSubtype(VenuesSectionDTO::class.java, NAME_SECTION_VENUES)