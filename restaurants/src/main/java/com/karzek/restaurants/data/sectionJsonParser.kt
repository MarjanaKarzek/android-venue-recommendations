package com.karzek.restaurants.data

import com.karzek.restaurants.data.RestaurantPageDTO.CategorySectionItemDTO
import com.karzek.restaurants.data.RestaurantPageDTO.SectionDTO
import com.karzek.restaurants.data.RestaurantPageDTO.VenueSectionItemDTO
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory

val sectionJsonParser: PolymorphicJsonAdapterFactory<SectionDTO> =
  PolymorphicJsonAdapterFactory.of(SectionDTO::class.java, "name")
    .withSubtype(CategorySectionItemDTO::class.java, "restaurants_page_categories")
    .withSubtype(VenueSectionItemDTO::class.java, "restaurants-delivering-venues")