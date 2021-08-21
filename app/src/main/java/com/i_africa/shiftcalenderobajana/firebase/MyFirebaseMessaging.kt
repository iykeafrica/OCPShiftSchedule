package com.i_africa.shiftcalenderobajana.firebase

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.i_africa.shiftcalenderobajana.utils.Constant
import javax.inject.Inject

private val TAG = MyFirebaseMessaging::class.simpleName
class MyFirebaseMessaging @Inject constructor(private val firebaseMessaging: FirebaseMessaging) {

    fun subscribe() {
        firebaseMessaging.subscribeToTopic(Constant.SUBSCRIBE_TO_SHIFT_CALENDAR)
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscription Failed"
                }
                Log.d(TAG, msg)
            }
    }

    fun generateNewToken() : String {
        var token = ""
        firebaseMessaging.token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            token = task.result!!
            Log.d(TAG, "This is your token: $token")
        })
        return  token
    }
}