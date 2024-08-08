package com.karzek.domain.restaurants

import com.karzek.core.result.Result

interface RestaurantRepository {
  suspend fun getRestaurants(
    latitude: String,
    longitude: String,
    limit: Int
  ): Result<List<Restaurant>>
}