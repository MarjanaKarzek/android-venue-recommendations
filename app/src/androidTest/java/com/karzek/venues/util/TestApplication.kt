package com.karzek.venues.util

import android.app.Application
import com.karzek.venues.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.Forest.plant

class TestApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidLogger()
      androidContext(this@TestApplication)
      modules(
        appModules.toMutableList().apply {
          add(testModule)
        }
      )
    }
    plant(Timber.DebugTree())
  }
}