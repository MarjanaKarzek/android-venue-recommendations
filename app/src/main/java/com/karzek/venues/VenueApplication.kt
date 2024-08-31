package com.karzek.venues

import android.app.Application
import com.karzek.core.error.CrashReportingTree
import com.karzek.venues.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber
import timber.log.Timber.Forest.plant


class VenueApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    setupKoin()
    setupTimber()
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      plant(Timber.DebugTree())
    } else {
      plant(CrashReportingTree())
    }
  }

  private fun setupKoin() {
    startKoin {
      androidLogger()
      androidContext(this@VenueApplication)
      modules(appModules)
    }
  }
}