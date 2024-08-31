package com.karzek.wishlist.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wishlist(
  @Json(name = "restaurantIds") val restaurantIds: List<String>
)