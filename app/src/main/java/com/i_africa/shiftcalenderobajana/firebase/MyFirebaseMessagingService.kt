package com.i_africa.shiftcalenderobajana.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.i_africa.shiftcalenderobajana.MainActivity
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.utils.Constant.BODY_KEY_FOREGROUND
import com.i_africa.shiftcalenderobajana.utils.Constant.TITLE_KEY_FOREGROUND

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val processLater = false
    private val NOTIFICATION_CHANEL_ID =
        "com.example.echange_app.background.worker_NOTIFICATION_CHANEL_ID"
    private val NOTIFICATION_FCM_TEST: CharSequence = "New notification"
    private val NOTIFICATION_ID = 6353


    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fcm_token", token).apply()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")


        if (/* Check if data needs to be processed by long running job */ processLater) {
            //scheduleJob()
            Log.d(TAG, "executing schedule job")
        } else {
            // Handle message within 10 seconds
            handleNow(remoteMessage)
        }
    }

    private fun handleNow(remoteMessage: RemoteMessage) {
        val handler = Handler(Looper.getMainLooper())

        handler.post {
            Toast.makeText(
                baseContext,
                getString(R.string.handle_notification_now),
                Toast.LENGTH_LONG
            ).show()

            remoteMessage.notification?.let {

                setNotification(
                    remoteMessage.notification!!.title!!,
                    remoteMessage.notification!!.body!!
                )
            }
        }
    }

    private fun setNotification(title: String, body: String) {
        Log.d(TAG, "setNotification: title is $title body is $body")
        val notificationIntent = Intent(applicationContext, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        notificationIntent.putExtra("Hello", title)
        notificationIntent.putExtra("Hi", body)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0, notificationIntent, PendingIntent.FLAG_ONE_SHOT
        )
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANEL_ID, NOTIFICATION_FCM_TEST, NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext, NOTIFICATION_CHANEL_ID
        )
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_logo)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    companion object {
        private const val TAG = "MyFirebaseMessagingS"
    }

}