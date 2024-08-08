package com.karzek.location.di

import com.karzek.domain.location.LocationRepository
import com.karzek.location.data.LocationRepositoryImpl
import org.koin.dsl.module

val locationModule = module {
  single<LocationRepository> { LocationRepositoryImpl(dispatcherProvider = get()) }
}