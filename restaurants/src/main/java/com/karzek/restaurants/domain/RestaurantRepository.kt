package com.karzek.restaurants.domain

import com.karzek.core.result.Result

interface RestaurantRepository {
  suspend fun getRestaurants(
    latitude: String,
    longitude: String,
    limit: Int
  ): Result<List<Restaurant>>
}