package com.karzek.venues

import android.app.Application

class VenueApplication : Application() {
  override fun onCreate() {
    super.onCreate()

   // startKoin {
  //    androidLogger()
   //   androidContext(this@VenueApplication)
   //   modules(appModules)
  //  }
  }
}