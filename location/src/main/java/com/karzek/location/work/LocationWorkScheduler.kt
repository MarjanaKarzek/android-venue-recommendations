package com.karzek.location.work

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest

class LocationWorkScheduler(private val context: Context) : WorkScheduler {
  override fun scheduleLocationNotification() {
    val workRequest: WorkRequest = OneTimeWorkRequest.Builder(LocationWorker::class.java)
      .build()

    WorkManager.getInstance(context).enqueue(workRequest)
  }

  override fun cancelAllWork() {
    WorkManager.getInstance(context).cancelAllWork()
  }
}