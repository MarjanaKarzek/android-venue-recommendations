package com.karzek.restaurants.domain

import com.karzek.core.error.FeatureError

data class VenueSectionNotFoundError(override val throwable: Throwable) : FeatureError(throwable)
data class NoRestaurantsFoundError(override val throwable: Throwable) : FeatureError(throwable)