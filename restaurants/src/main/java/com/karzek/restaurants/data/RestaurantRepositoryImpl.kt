package com.karzek.restaurants.data

import com.karzek.core.coroutines.DispatcherProvider
import com.karzek.core.error.ErrorEntityFactory
import com.karzek.core.mapper.Mapper
import com.karzek.core.result.Result
import com.karzek.core.result.safeApiCall
import com.karzek.restaurants.data.api.NAME_SECTION_VENUES
import com.karzek.restaurants.data.api.RestaurantPageDTO.VenueSectionItemDTO
import com.karzek.restaurants.data.api.RestaurantPageDTO.VenuesSectionDTO
import com.karzek.restaurants.data.api.RestaurantsApi
import com.karzek.restaurants.domain.Restaurant
import com.karzek.restaurants.domain.RestaurantRepository
import com.karzek.restaurants.data.error.RestaurantsNotFound
import com.karzek.restaurants.data.error.VenueSectionNotFound

class RestaurantRepositoryImpl(
  private val api: RestaurantsApi,
  private val mapper: Mapper<VenueSectionItemDTO, Restaurant>,
  private val dispatcher: DispatcherProvider,
  private val errorEntityFactory: ErrorEntityFactory,
) : RestaurantRepository {
  override suspend fun getRestaurants(
    latitude: Double,
    longitude: Double
  ): Result<List<Restaurant>> {
    return safeApiCall(
      coroutineContext = dispatcher.io,
      errorFactory = errorEntityFactory,
    ) {
      val section = api.getRestaurants(
        latitude = latitude,
        longitude = longitude
      ).sections.find { it.name == NAME_SECTION_VENUES } as? VenuesSectionDTO

      checkNotNull(section) { throw VenueSectionNotFound() }
      val restaurants = section.items.map { mapper.convert(it) }
      check(restaurants.isEmpty()) { throw RestaurantsNotFound() }
      restaurants
    }
  }
}