package com.karzek.venues.util

import com.karzek.core.network.NetworkConfiguration
import com.karzek.location.work.WorkScheduler
import org.koin.dsl.module
import timber.log.Timber

val testModule = module {
  single<NetworkConfiguration> {
    object : NetworkConfiguration {
      override val baseUrl: String
        get() = "http://localhost:8283/"
    }
  }
  single<WorkScheduler> {
    object : WorkScheduler {
      override fun scheduleLocationNotification() {
        Timber.d("Mocked Work scheduled")
      }

      override fun cancelAllWork() {
        Timber.d("Mocked Work cancelled")
      }
    }
  }
}