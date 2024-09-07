package com.karzek.location.work

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.koin.java.KoinJavaComponent.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * This is how I would implement the background update. In a production app I would challenge this
 * kind of approach though keeping the user experience in mind.
 *
 * Using OneTimeWorkRequest as the repeating one has a minimum runtime of 15 minutes.
 */
class LocationWorker(context: Context, params: WorkerParameters) :
  Worker(context, params) {

  private val applicationContext: Application by inject(Application::class.java)

  override fun doWork(): Result {
    if (!isAppInForeground()) {
      Timber.d("Worker execution success")
      // fetch the location and for example show a notification to return to app
      rescheduleWorker()
    }
    return Result.success()
  }

  private fun rescheduleWorker() {
    val workRequest: WorkRequest =
      OneTimeWorkRequest.Builder(LocationWorker::class.java)
        .setInitialDelay(10, TimeUnit.SECONDS)
        .build()

    WorkManager.getInstance(applicationContext).enqueue(workRequest)
  }

  private fun isAppInForeground(): Boolean {
    val appProcesses =
      (applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).runningAppProcesses
    return appProcesses.any { it.processName == applicationContext.packageName && it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND }
  }
}