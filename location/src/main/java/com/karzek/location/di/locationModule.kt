package com.karzek.location.di

import com.karzek.domain.location.LocationRepository
import com.karzek.location.data.LocationRepositoryImpl
import com.karzek.location.work.LocationNotificationWorkScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationModule = module {
  single<LocationRepository> { LocationRepositoryImpl(dispatcherProvider = get()) }
  single { LocationNotificationWorkScheduler(context = androidContext()) }
}