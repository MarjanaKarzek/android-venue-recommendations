package com.karzek.restaurants.data.error

import com.karzek.core.error.ErrorEntity
import com.karzek.core.error.ModuleErrorEntityFactory
import com.karzek.domain.restaurants.NoRestaurantsFoundError
import com.karzek.domain.restaurants.VenueSectionNotFoundError

class RestaurantsErrorEntityFactory : ModuleErrorEntityFactory {
  override fun toError(throwable: Throwable): ErrorEntity? {
    return when (throwable) {
      is VenueSectionNotFound -> com.karzek.domain.restaurants.VenueSectionNotFoundError(throwable)
      is RestaurantsNotFound -> com.karzek.domain.restaurants.NoRestaurantsFoundError(throwable)
      else -> null
    }
  }
}