package com.karzek.restaurants.data.error

import com.karzek.core.error.ErrorEntity
import com.karzek.core.error.FeatureErrorEntityFactory
import com.karzek.domain.restaurants.NoRestaurantsFoundError
import com.karzek.domain.restaurants.VenueSectionNotFoundError

class RestaurantsErrorEntityFactory : FeatureErrorEntityFactory {
  override fun toError(throwable: Throwable): ErrorEntity? {
    return when (throwable) {
      is VenueSectionNotFound -> VenueSectionNotFoundError(throwable)
      is RestaurantsNotFound -> NoRestaurantsFoundError(throwable)
      else -> null
    }
  }
}