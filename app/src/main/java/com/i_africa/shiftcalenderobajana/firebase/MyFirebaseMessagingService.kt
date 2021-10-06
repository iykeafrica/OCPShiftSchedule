package com.i_africa.shiftcalenderobajana.firebase

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import com.i_africa.shiftcalenderobajana.common.firebase_baseservice.BaseFirebaseMessagingService
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_BODY_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_TOKEN
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_LINK_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_TITLE_KEY
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private val TAG = MyFirebaseMessagingService::class.simpleName

@AndroidEntryPoint
class MyFirebaseMessagingService : BaseFirebaseMessagingService() {
    private val processLater = false
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var notification: Notification

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: MyFirebaseMessagingService $mySharedPreferences")
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

        val title = remoteMessage.data[FCM_TITLE_KEY]!!
        val body = remoteMessage.data[FCM_BODY_KEY]!!
        val link = remoteMessage.data[FCM_LINK_KEY]!!
        handler.post {
            notification.setNotification(title, body, link)
        }

    }

}