package com.karzek.location.di

import com.karzek.domain.location.LocationRepository
import com.karzek.location.data.LocationRepositoryImpl
import com.karzek.location.work.LocationWorkScheduler
import com.karzek.location.work.WorkScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationModule = module {
  single<LocationRepository> { LocationRepositoryImpl(dispatcherProvider = get()) }
  single<WorkScheduler> { LocationWorkScheduler(context = androidContext()) }
}