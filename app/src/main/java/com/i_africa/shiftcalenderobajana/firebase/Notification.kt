package com.i_africa.shiftcalenderobajana.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftActivity
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_BODY_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_TITLE_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_LINK_KEY

object Notification {
    fun setNotification(applicationContext: Context, title: String, body: String, link: String) {

        val notificationIntent = Intent(applicationContext, SelectShiftActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        notificationIntent.putExtra(FCM_TITLE_KEY, title)
        notificationIntent.putExtra(FCM_BODY_KEY, body)
        notificationIntent.putExtra(FCM_LINK_KEY, link)

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0, notificationIntent, PendingIntent.FLAG_ONE_SHOT
        )

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                Constant.NOTIFICATION_CHANNEL_ID,
                Constant.NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext, Constant.NOTIFICATION_CHANNEL_ID
        )
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_notification)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        notificationManager.notify(Constant.NOTIFICATION_ID, notificationBuilder.build())
    }
}