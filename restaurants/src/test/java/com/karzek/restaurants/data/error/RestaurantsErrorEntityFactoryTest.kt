package com.karzek.restaurants.data.error

import com.karzek.core.error.FeatureErrorEntityFactory
import com.karzek.domain.restaurants.NoRestaurantsFoundError
import com.karzek.domain.restaurants.VenueSectionNotFoundError
import org.junit.jupiter.api.Test

class RestaurantsErrorEntityFactoryTest {

  private val errorEntityFactory: FeatureErrorEntityFactory = RestaurantsErrorEntityFactory()

  @Test
  fun `WHEN toError is called AND error is VenueSectionNotFound THEN expected FeatureError is returned`() {
    val error = VenueSectionNotFound()
    val result = errorEntityFactory.toError(error)

    assert(result == VenueSectionNotFoundError(error))
  }

  @Test
  fun `WHEN toError is called AND error is RestaurantsNotFound THEN expected FeatureError is returned`() {
    val error = RestaurantsNotFound()
    val result = errorEntityFactory.toError(error)

    assert(result == NoRestaurantsFoundError(error))
  }

  @Test
  fun `WHEN toError is called AND error is unknown THEN null is returned`() {
    val error = IllegalStateException()
    val result = errorEntityFactory.toError(error)

    assert(result == null)
  }
}