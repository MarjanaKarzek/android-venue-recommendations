package com.karzek.restaurants

import android.app.Application
import com.karzek.restaurants.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class VenueApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@VenueApplication)
      modules(appModules)
    }
  }
}