package com.karzek.location.work

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest

class LocationNotificationWorkScheduler(private val context: Context) {
  fun scheduleLocationNotification() {
    val workRequest: WorkRequest = OneTimeWorkRequest.Builder(LocationNotificationWorker::class.java)
      .build()

    WorkManager.getInstance(context).enqueue(workRequest)
  }

  fun cancelAllWork() {
    WorkManager.getInstance(context).cancelAllWork()
  }
}