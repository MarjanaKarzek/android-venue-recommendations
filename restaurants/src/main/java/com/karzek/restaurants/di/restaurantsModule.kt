package com.karzek.restaurants.di

import com.karzek.core.di.getMapper
import com.karzek.core.di.mapper
import com.karzek.core.error.ComposedErrorEntityFactory
import com.karzek.core.network.NetworkErrorEntityFactory
import com.karzek.restaurants.data.RestaurantRepositoryImpl
import com.karzek.restaurants.data.mapper.RestaurantMapper
import com.karzek.restaurants.domain.RestaurantRepository
import com.karzek.restaurants.data.error.RestaurantsErrorEntityFactory
import org.koin.dsl.module

val restaurantsModule = module {
  single<RestaurantRepository> {
    RestaurantRepositoryImpl(
      api = get(),
      mapper = getMapper(),
      dispatcher = get(),
      errorEntityFactory = getErrorEntityFactory(),
    )
  }

  mapper { RestaurantMapper() }
}

private fun getErrorEntityFactory() = ComposedErrorEntityFactory(
  listOf(
    RestaurantsErrorEntityFactory(),
    NetworkErrorEntityFactory,
  )
)