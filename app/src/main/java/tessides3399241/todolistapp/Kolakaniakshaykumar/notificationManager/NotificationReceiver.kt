package tessides3399241.todolistapp.Kolakaniakshaykumar.notificationManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import tessides3399241.todolistapp.Kolakaniakshaykumar.MainActivity
import tessides3399241.todolistapp.Kolakaniakshaykumar.R
import kotlin.random.Random


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val title = intent?.getStringExtra("title") ?: "Scheduled Notification"
        val message = intent?.getStringExtra("message") ?: "It's time!"

        showNotification(context, title, message)
    }

    private fun showNotification(context: Context, title: String, message: String) {
        val channelId = "scheduled_notifications"
        val notificationId = Random.nextInt()

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel (required for Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Scheduled Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for scheduled notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}