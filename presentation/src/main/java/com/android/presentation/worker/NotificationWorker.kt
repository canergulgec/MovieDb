package com.android.presentation.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.android.presentation.R
import com.caner.common.Constants
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters
) : Worker(context, workerParams) {

    private lateinit var collapsedView: RemoteViews
    private lateinit var expandedView: RemoteViews

    override fun doWork(): Result {
        val movieTitle = inputData.getString(Constants.MOVIE_TITLE)
        val movieOverview = inputData.getString(Constants.MOVIE_OVERVIEW)
        createNotificationChannel()

        collapsedView = RemoteViews(context.packageName, R.layout.notification_small)
        expandedView = RemoteViews(context.packageName, R.layout.notification_large)
        setMovieInformation(movieTitle, movieOverview)

        val customNotification = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(collapsedView)
            .setCustomBigContentView(expandedView)
            .build()
        NotificationManagerCompat.from(context).notify(0, customNotification)

        return Result.success()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Constants.CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setMovieInformation(
        movieTitle: String?,
        movieOverview: String?
    ) {
        collapsedView.setTextViewText(R.id.notificationMovieTitleTv, movieTitle)
        expandedView.setTextViewText(R.id.notificationLargeTitleTv, movieTitle)
        expandedView.setTextViewText(R.id.notificationLargeOverviewTv, movieOverview)
    }
}
