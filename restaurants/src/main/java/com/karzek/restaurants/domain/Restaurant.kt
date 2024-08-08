package com.karzek.restaurants.domain

// in the real world the domain model would represent the dto better, but I took a shortcut here
data class Restaurant(
  val id: String,
  val name: String,
  val shortDescription: String?,
  val imageUrl: String,
)