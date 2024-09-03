package com.karzek.venues.ui

import com.karzek.designsystem.R
import com.karzek.designsystem.card.CardData
import com.karzek.domain.restaurants.Restaurant
import com.karzek.domain.restaurants.RestaurantOutput

object MainViewProvider {
  fun getViewItems(
    data: RestaurantOutput,
    onWishIconClicked: (Restaurant, Boolean) -> Unit
  ): List<CardData> {
    return data.restaurants.map {
      CardData(
        title = it.name,
        description = it.shortDescription,
        imageUrl = it.imageUrl,
        icon = getWishIcon(data.restaurantIds, it),
        onIconClicked = { onWishIconClicked(it, data.restaurantIds.contains(it.id)) }
      )
    }
  }

  private fun getWishIcon(
    restaurantIds: List<String>,
    restaurant: Restaurant
  ) = if (restaurantIds.contains(restaurant.id)) {
    R.drawable.ic_heart_filled
  } else {
    R.drawable.ic_heart_outlined
  }

}