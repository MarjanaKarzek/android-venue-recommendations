package com.karzek.restaurants.domain

import com.karzek.core.result.Result

interface RestaurantRepository {
  suspend fun getRestaurants(latitude: Double, longitude: Double): Result<List<Restaurant>>
}