package com.karzek.restaurants.di

import com.karzek.core.di.getMapper
import com.karzek.core.di.mapper
import com.karzek.core.error.ComposedErrorEntityFactory
import com.karzek.core.network.NetworkErrorEntityFactory
import com.karzek.domain.restaurants.RestaurantRepository
import com.karzek.restaurants.data.RestaurantRepositoryImpl
import com.karzek.restaurants.data.api.RestaurantsApi
import com.karzek.restaurants.data.error.RestaurantsErrorEntityFactory
import com.karzek.restaurants.data.mapper.RestaurantMapper
import org.koin.dsl.module
import retrofit2.Retrofit

val restaurantModule = module {
  single<RestaurantRepository> {
    RestaurantRepositoryImpl(
      api = get(),
      mapper = getMapper(),
      dispatcher = get(),
      errorEntityFactory = getErrorEntityFactory(),
    )
  }

  mapper { RestaurantMapper() }

  single<RestaurantsApi> { get<Retrofit>().create(RestaurantsApi::class.java) }
}

private fun getErrorEntityFactory() = ComposedErrorEntityFactory(
  listOf(
    RestaurantsErrorEntityFactory(),
    NetworkErrorEntityFactory,
  )
)