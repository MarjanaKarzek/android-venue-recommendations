package com.karzek.restaurants.data.error

import com.karzek.core.error.ErrorEntity
import com.karzek.core.error.ModuleErrorEntityFactory
import com.karzek.restaurants.domain.NoRestaurantsFoundError
import com.karzek.restaurants.domain.VenueSectionNotFoundError

class RestaurantsErrorEntityFactory : ModuleErrorEntityFactory {
  override fun toError(throwable: Throwable): ErrorEntity? {
    return when (throwable) {
      is VenueSectionNotFound -> VenueSectionNotFoundError(throwable)
      is RestaurantsNotFound -> NoRestaurantsFoundError(throwable)
      else -> null
    }
  }
}