package com.karzek.venues.di

import com.karzek.core.di.coreModule
import com.karzek.core.di.networkingModule
import com.karzek.restaurants.di.restaurantsModule

val appModules = listOf(
  coreModule,
  networkingModule,
  restaurantsModule,
  moshiModule,
)