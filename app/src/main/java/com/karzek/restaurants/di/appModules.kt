package com.karzek.restaurants.di

import com.karzek.core.di.networkingModule

val appModules = listOf(
  networkingModule,
  restaurantsModule,
  moshiModule,
)