package com.karzek.restaurants.data

import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApi {

  @GET("/restaurants")
  suspend fun getRestaurants(
    @Query("lat") latitude: Double,
    @Query("lon") longitude: Double,
  ): RestaurantPageDTO
}