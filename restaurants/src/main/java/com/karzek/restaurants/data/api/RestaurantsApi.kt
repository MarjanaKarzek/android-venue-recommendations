package com.karzek.restaurants.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApi {

  @GET("v1/pages/restaurants")
  suspend fun getRestaurants(
    @Query("lat") latitude: String,
    @Query("lon") longitude: String,
  ): RestaurantPageDTO
}