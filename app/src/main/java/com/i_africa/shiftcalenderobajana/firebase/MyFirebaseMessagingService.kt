package com.i_africa.shiftcalenderobajana.firebase

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import com.i_africa.shiftcalenderobajana.common.firebase_baseservice.BaseFirebaseMessagingService
import com.i_africa.shiftcalenderobajana.firebase.Notification.setNotification
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_TOKEN
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_LINK_KEY
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import javax.inject.Inject

private val TAG = MyFirebaseMessagingService::class.simpleName

class MyFirebaseMessagingService : BaseFirebaseMessagingService() {
    private val processLater = false
    @Inject lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate() {
        injector.inject(this)
        Log.d(TAG, "onCreate: MyFirebaseMessagingService $mySharedPreferences")
        super.onCreate()
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        mySharedPreferences.storeStringValue(FCM_TOKEN, token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")

        if (processLater) {/* Check if data needs to be processed by long running job */
            //scheduleJob()
            Log.d(TAG, "executing schedule job")
        } else {// Handle message within 10 seconds
            handleNow(remoteMessage)
        }
    }

    private fun handleNow(remoteMessage: RemoteMessage) {
        val handler = Handler(Looper.getMainLooper())

        handler.post {
            remoteMessage.notification?.let {
                setNotification(
                    applicationContext,
                    remoteMessage.notification!!.title!!,
                    remoteMessage.notification!!.body!!,
                    remoteMessage.data[FCM_LINK_KEY]!!
                )
            }
        }
    }


}