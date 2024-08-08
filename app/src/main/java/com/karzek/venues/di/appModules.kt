package com.karzek.venues.di

import com.karzek.core.di.networkingModule

val appModules = listOf(
  networkingModule,
  venueModule,
)