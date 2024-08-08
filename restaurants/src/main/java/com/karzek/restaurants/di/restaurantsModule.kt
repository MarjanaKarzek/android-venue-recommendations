package com.karzek.restaurants.di

import com.karzek.core.di.getMapper
import com.karzek.core.di.mapper
import com.karzek.restaurants.data.RestaurantRepositoryImpl
import com.karzek.restaurants.data.mapper.RestaurantMapper
import com.karzek.restaurants.domain.RestaurantRepository
import org.koin.dsl.module

val restaurantsModule = module {
  single<RestaurantRepository> {
    RestaurantRepositoryImpl(
      api = get(),
      mapper = getMapper(),
      dispatcher = get(),
      errorEntityFactory = get()
    )
  }

  mapper { RestaurantMapper() }
}