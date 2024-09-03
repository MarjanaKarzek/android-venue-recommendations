package com.karzek.location.work

import android.app.ActivityManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.karzek.domain.location.LocationRepository
import org.koin.java.KoinJavaComponent.inject
import java.util.concurrent.TimeUnit

/**
 * Using OneTimeWorkRequest as the repeating one has a minimum runtime of 15 minutes.
 */
class LocationNotificationWorker(context: Context, params: WorkerParameters) :
  Worker(context, params) {

  private val applicationContext: Application by inject(Application::class.java)
  private val locationRepository: LocationRepository by inject(LocationRepository::class.java)

  override fun doWork(): Result {
    showNotification()
    if (!isAppInForeground()) {
      val workRequest: WorkRequest =
        OneTimeWorkRequest.Builder(LocationNotificationWorker::class.java)
          .setInitialDelay(10, TimeUnit.SECONDS)
          .build()

      WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
    return Result.success()
  }

  private fun showNotification() {
    val location = locationRepository.getUserLocation()
    val notificationManager =
      applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "background_notification_channel"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        channelId,
        "Background Notifications",
        NotificationManager.IMPORTANCE_DEFAULT
      )
      notificationManager.createNotificationChannel(channel)
    }

    val notification = NotificationCompat.Builder(applicationContext, channelId)
      .setContentTitle("Background Notification")
      .setContentText("Your location changed to lat:${location.latitude} long:${location.longitude}. Open the app to see recommendations near by.")
      .setSmallIcon(android.R.drawable.ic_dialog_info)
      .build()

    notificationManager.notify(1, notification)
  }

  private fun isAppInForeground(): Boolean {
    val appProcesses =
      (applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).runningAppProcesses
    return appProcesses.any { it.processName == applicationContext.packageName && it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND }
  }
}