package com.karzek.venues.di

import com.karzek.core.di.coreModule
import com.karzek.core.di.networkingModule
import com.karzek.restaurants.di.restaurantModule
import com.karzek.wishlist.di.wishlistDataModule

val appModules = listOf(
  baseModule,
  coreModule,
  networkingModule,
  restaurantModule,
  wishlistDataModule,
)